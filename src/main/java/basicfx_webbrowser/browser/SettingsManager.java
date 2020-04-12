package basicfx_webbrowser.browser;

import java.io.FileInputStream;
import java.io.InputStream;

import org.json.simple.JSONObject;

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
            return (boolean) json.get("restore");
        return false;
    }

    public String getTheme() {
        if (json.containsKey("theme"))
            return "/theme/"+ (String) json.get("theme");
        return "/theme/"+ "bootstrap3.css";
    }

    public boolean isMenuPinned() {
        if (json.containsKey("pinMenu"))
            return (boolean) json.get("pinMenu");
        return false;
    }




    // - Public Settings value Setters ----------






    // - Class Public Methods ------------





    // - Class Private Methods -----------





    // - Private attribute Getters and Setters --------






    // - Default Settings -------------
    
}