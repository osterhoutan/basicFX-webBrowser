package basicfx_webbrowser.myfx.browser.tab;

import java.net.URL;

import basicfx_webbrowser.Global;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;


/**
 * A multipurpose browser navigation bar like yu see in Google Chrome but with fewer features.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class OmniBar extends TextField {

    // - Class Attributes -----------



    // - Class Constructors ----------
    public OmniBar() {
        super();
        this.focusedProperty().addListener((ob, ov, nv) -> {
            if (nv) this.selectAll();
        });
    }


    // - Public Class Methods ----------
    public String getURL() {
        String input = this.getText().trim();
        if(validURL(input))
            return input;
        try {
            input = Global.settings.getSearchEngine().compileQueryURL(input);
        } catch (Exception ex) {
            System.out.println("\n\n\tERROR: SearchEngine.compileQueryURL() failed to make a valid URL\n"); // DEBUG: See search URL's are being made properly 
            return null;
        }
        return input;
    }


    public void setIsDisable(boolean value) {
        this.setDisable(value);
    }

    
    public void setOnActionOver(EventHandler<ActionEvent> lambda) {
        this.setOnAction(lambda);
    }


    // - Private Class Methods -----------
    private boolean validURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    

 
}