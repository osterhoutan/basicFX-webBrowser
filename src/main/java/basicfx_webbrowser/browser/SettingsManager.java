package basicfx_webbrowser.browser;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Settings Manager for a web Browser.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class SettingsManager {

    // - Class Attributes -------



    // - Class Constructors -------
    public SettingsManager(FileInputStream file) {

    }


    // - Public Settings value Getter --------

    public boolean doRestore() {
        return false;
    }

    public String getTheme() {
        return "/theme/"+ "bootstrap3.css";
    }




    // - Public Settings value Setters ----------






    // - Class Public Methods ------------





    // - Class Private Methods -----------





    // - Private attribute Getters and Setters --------

}