package basicfx_webbrowser.browser;

import java.util.ArrayList;
import java.util.Set;

import org.json.simple.JSONObject;

/**
 * Bookmark manger class for a web browser.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public final class BookmarkManager extends JsonManager<JSONObject> {

    // - Class Attributes -------



    // - Class Constructors -------
    public BookmarkManager() {
        
    }


    // - Public Settings value Getter --------
    public Set<String> getBookmarkNames() {
        return (Set<String>) json.keySet();
    }

    public String getURL(String name) {
        return (String) json.get(name);
    }

    public boolean isBookmark(String url) {
        return json.containsValue(url);
    }

    public boolean addBookmark(String name, String url) {
        name = name.trim();
        if (json.containsKey(name)) 
            name = addBookmark(name, url, 1);
        Object result = json.put(name, url.trim());
        try { this.write(); } catch (Exception ex) {System.out.println("\n\n\tERROR: failed to save bookmarks.\n");}
        return result==null;
    }

    private String addBookmark(String name, String url, int modifier) {
        String newName = name+" ("+ modifier+")";
        if (json.containsKey(newName))
            return addBookmark(name, url, ++modifier);
        return newName;
    }

    public boolean updateBookmark(String oldName, String name, String url) {
        if (!json.containsKey(oldName)) return false;
        json.remove(oldName);
        return addBookmark(name, url);
    }
    
    public boolean updateBookmark(String oldName, String name) {
        if (!json.containsKey(oldName)) return false;
        String url = (String) json.remove(oldName);
        return addBookmark(name, url);
    }

    public boolean removeBookmark(String name) {
        if (json.containsKey(name)) {
            json.remove(name);
            return true;
        }
        return false;
    }

    public void removeBookmarkByURL(String url) {
        json.forEach((key, value) -> {
            String entry = (String) value;
            if (entry.equals(url))
                json.remove(key);
        });
    }


}