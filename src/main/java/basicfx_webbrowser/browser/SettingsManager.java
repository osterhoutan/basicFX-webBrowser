package basicfx_webbrowser.browser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.browser.settings.SearchEngine;

/**
 * Settings Manager for a web Browser.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public final class SettingsManager extends JsonManager<JSONObject> {

    // - Class Attributes -------
    private File builtInThemeDir;
    private File userThemeDir;
    private ArrayList<String> builtInThemes = new ArrayList<>();
    private ArrayList<String> userThemes = new ArrayList<>();
    private ArrayList<File> userThemeFiles = new ArrayList<>();
    private ArrayList<String> themeNames = new ArrayList<>();

    // - Class Constructors -------
    public SettingsManager() {
    }


    // - Public Settings value Getter --------

    public boolean doRestore() {
        if (json.containsKey("restore"))
            return (Boolean) json.get("restore");
        return false;
    }

    public URL getTheme() throws MalformedURLException {
        String themeName = getThemeName();
        int index = userThemes.indexOf(themeName+".css");
        if (index>0)
            return userThemeFiles.get(index).toURI().toURL();
        if (builtInThemes.contains(themeName+".css"))
            return getClass().getResource("/theme/"+themeName+".css"); 
        return getClass().getResource("/theme/bootstrap3.css");
    }

    public String getThemeName() {
        if (json.containsKey("theme"))
            return (String) json.get("theme");
        return "bootstrap3";
    }

    public List<String> getThemeNameList() {
        updateKnownThemes();
        builtInThemes.forEach((item) -> {
            String name = ((String) item).replaceAll("\\.css$", "");
            if (!themeNames.contains(name)) themeNames.add(name);
        });
        userThemes.forEach((item) -> {
            String name = ((String) item).replaceAll("\\.css$", "");
            if (!themeNames.contains(name)) themeNames.add(name);
        });
        return themeNames;
    }

    public boolean getMenuState() {
        if (json.containsKey("menuState"))
            return (Boolean) json.get("menuState");
        return false;
    }

    // public double getMenuSize() {
    //     if (json.containsKey("menuSize"))
    //         return (Double) json.get("menuSize");
    //     return 128;
    // }
    

    public SearchEngine getSearchEngine() {
        if (!json.containsKey("searchEngine")) 
            return SearchEngine.GOOGLE;
        SearchEngine eng = SearchEngine.fromName((String) json.get("searchEngine"));
        if (eng == null) 
            return SearchEngine.GOOGLE;
        if (eng == SearchEngine.CUSTOM) 
            return getCustomSearchEngine();
        return eng;
    }


    public SearchEngine getCustomSearchEngine() {
        if (!json.containsKey("customSearchEngine")) 
            return SearchEngine.GOOGLE;
        JSONObject values = (JSONObject) json.get("customSearchEngine");
        String queryUrl = (values.containsKey("queryURL")) ? (String) values.get("queryURL") : "not a url!"; 
        if (values.containsKey("searchHeader"))
            return SearchEngine.setCustomEngine(
                    "Custom",
                    (queryUrl.matches(Global.urlRegEx)) ? queryUrl : SearchEngine.CUSTOM.getQueryURL(),
                    (String) values.get("searchHeader")
                );
        return SearchEngine.setCustomEngine(
                (values.containsKey("name")) ? (String) values.get("name") : "Custom",
                (queryUrl.matches(Global.urlRegEx)) ? queryUrl : SearchEngine.CUSTOM.getQueryURL()
            );
    }

    public List<String> getSearchEngineList() {
        ArrayList<String> temp = new ArrayList<>();
        for (SearchEngine eng : SearchEngine.values()) 
            if (!eng.getName().equals("Custom"))
                temp.add(eng.getName());
        temp.add("Custom");
        return temp;
    }


    public String getHomePage() {
        if (!json.containsKey("homePage"))
            return "https://www.google.com";
        String url = (String) json.get("homePage");
        if (isValidURL(url)) return url;
        return getErrorPage().toExternalForm();
    }


    public String getStartPage() {
        if (isNewTabHome()) 
            return getNewTabPage().toExternalForm();
        return getHomePage();
    }


    public boolean isNewTabHome() {
        if (json.containsKey("newTabAsHomePage"))
            return (Boolean) json.get("newTabAsHomePage");
        return true;
    }

    public URL getNewTabPage() {
        if (json.containsKey("newTabPage"))
            try {
                return getAsset((String) json.get("newTabPage"), "html/newTab/");
            } catch (Exception ex) {
                System.err.println("\n\n\tERROR: User Defined `\"newTabPage\"` asset-location/file-path as specified in `settings.json` does not exist\n");
                ex.printStackTrace(System.err);  
            }
        return getClass().getResource("/html/newTab/default/tab.html");
    }


    public URL getErrorPage() {
        if (json.containsKey("errorPage"))
            try {
                return getAsset((String) json.get("newTabPage"), "html/error/");
            } catch (Exception ex) {
                System.err.println("\n\n\tERROR: User Defined `\"errorPage\"` asset-location/file-path as specified in `settings.json` does not exist\n");
                ex.printStackTrace(System.err);  
            }
        return getClass().getResource("/html/error/default/index.html");
    }


    // - Public Settings value Setters ----------

    public void setMenuState(Boolean isShown) {
        json.put("menuState", isShown);
    }

    public void toggleMenuState() {
        if (!json.containsKey("menuState")) json.put("menuState", (Boolean) false);
        json.put("menuState", !((Boolean) json.get("menuState")));
    }


    public void setDoRestore(Boolean value) {
        json.put("restore", value);
    }


    public void setNewTabHome(Boolean value) {
        json.put("newTabAsHomePage", value);
    }

    public boolean setHomePage(String url) {
        if (!isValidURL(url)) return false;
        json.put("homePage", url);
        return true;
    }

    public void setTheme(String name) {
        json.put("theme", name);
    }

    public void setSearchEngine(String name) {
        json.put("searchEngine", name);
    }

    public boolean setCustomSearchEngine(String header, String queryURL) {
        if (!header.matches("^[a-z]{3,16}:$") && header!="") return false;
        if (!queryURL.matches(Global.urlRegEx) || queryURL.matches("^.{16,}%s.*$")) return false;
        JSONObject temp = new JSONObject();
        temp.put("type", "SearchEngine");
        temp.put("searchHeader", header);
        temp.put("querryURL", queryURL);
        temp.put("name", "Custom");
        return true;
    }



    // - Class Public Methods ------------
    @Override
    public void read() throws Exception {
        super.read();
        builtInThemeDir = new File(getClass().getResource("/theme").toURI());
        userThemeDir = new File(Global.appDataDir+"themes");
        builtInThemes.addAll(Arrays.asList(builtInThemeDir.list()));
        userThemes.addAll(Arrays.asList(userThemeDir.list()));
        userThemeFiles.addAll(Arrays.asList(userThemeDir.listFiles()));
    }

    public String getFilePath() {
        return file.getAbsolutePath();
    }



    // - Class Private Methods -----------

    private boolean isValidURL(String url) {
        if (url.matches(Global.urlRegEx))
            try {
                new URL(url).toURI();
                return true;
            } catch (Exception ex) {
                return false;
            }
        return false;
    }

    private void updateKnownThemes() {
        try {
            builtInThemeDir = new File(getClass().getResource("/theme").toURI());
            userThemeDir = new File(Global.appDataDir+"themes");
            builtInThemes.addAll(Arrays.asList(builtInThemeDir.list()));
            userThemes.addAll(Arrays.asList(userThemeDir.list()));
            userThemeFiles.addAll(Arrays.asList(userThemeDir.listFiles()));
        } catch (Exception ex) {
            System.err.println("\n\n\tERROR: failed to find built in Themes Directory (SettingsManager.updateKnownThemes(): 175)\n\n");
            ex.printStackTrace(System.err);
        }
    }

    private boolean isAbsoluteFilePath(String path) {
        return path.matches(Global.absFilePathRegEx);
    }


    private URL getAsset(String asset, String defaultDir) throws Exception {
        if (isValidURL(asset)) return new URL(asset);
        if (isAbsoluteFilePath(asset)) 
            return (new File(asset)).toURI().toURL();
        try {
            return (new File(Global.appDataDir+defaultDir+asset)).toURI().toURL();
        } catch (MalformedURLException ex) {
            return getClass().getResource('/'+defaultDir+asset).toURI().toURL();
        }
    }




    // - Private attribute Getters and Setters --------






    // - Default Settings -------------
    
}