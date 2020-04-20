package basicfx_webbrowser.browser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.json.simple.JSONObject;

import basicfx_webbrowser.browser.settings.SearchEngine;

/**
 * Settings Manager for a web Browser.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public final class SettingsManager extends JsonManager<JSONObject> {

    // - Class Attributes -------
    


    // - Class Constructors -------
    public SettingsManager() {
        
    }


    // - Public Settings value Getter --------

    public boolean doRestore() {
        if (json.containsKey("restore"))
            return (Boolean) json.get("restore");
        return false;
    }

    public String getTheme() {
        if (json.containsKey("theme"))
            return "/theme/"+ (String) json.get("theme");
        return "/theme/"+ "bootstrap3.css";
    }

    public boolean isMenuPinned() {
        if (json.containsKey("pinMenu"))
            return (Boolean) json.get("pinMenu");
        return false;
    }
    

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


    public String getHomePage() {
        if (isNewTabHome()) return "https://www.google.com";
        if (!json.containsKey("homePage"))
            return "https://www.google.com";
        String url = (String) json.get("homePage");
        if (isValidURL(url)) return url;
        return "https://www.google.com";
    }


    public boolean isNewTabHome() {
        if (json.containsKey("newTabAsHomePage"))
            return (Boolean) json.get("newTabAsHomePage");
        return true;
    }


    public String getErrorPage() {
        if (json.containsKey("errorPage"))
            return "/theme/"+ (String) json.get("errorPage");
        return "/theme/"+ "404.html";
    }


    // - Public Settings value Setters ----------






    // - Class Public Methods ------------





    // - Class Private Methods -----------

    private SearchEngine getCustomSearchEngine() {
        if (!json.containsKey("customSearchEngine")) 
            return SearchEngine.GOOGLE;
        JSONObject values = (JSONObject) json.get("customSearchEngine");
        if (values.containsKey("searchHeader"))
            return SearchEngine.setCustomEngine(
                    (values.containsKey("name")) ? (String) values.get("name") : "Custom",
                    (values.containsKey("queryURL")) ? (String) values.get("queryURL") : "",
                    (String) values.get("searchHeader")
                );
        return SearchEngine.setCustomEngine(
                (values.containsKey("name")) ? (String) values.get("name") : "Custom",
                (values.containsKey("queryURL")) ? (String) values.get("queryURL") : ""
            );
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }




    // - Private attribute Getters and Setters --------






    // - Default Settings -------------
    
}