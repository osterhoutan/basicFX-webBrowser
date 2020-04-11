package basicfx_webbrowser.control;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// import org.apache.commons.validator.UrlValidator;

import basicfx_webbrowser.myfx.browser.tab.OmniBar;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
// - javafx imports ----
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class for the {@code tab.fxml} ({@code basicfx_webbrowser/fxml/browser/tab/tab.fxml}) file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class WebTabController extends FXML_Controller {

    // - FXML Objects --------
    @FXML private VBox tabRoot;
    @FXML private HBox webBar;
    @FXML private HBox browserBar;
    @FXML private Button backBtn;
    @FXML private Button fwdBtn;
    @FXML private Button refreshBtn;
    @FXML private Button bookmarkBtn;
    @FXML private OmniBar omnibar;
    @FXML private WebView webView;


    // - Controller Attributes --------
    private WebEngine engine;
    private String url = "http://google.com";
    // private UrlValidator urlValidator = new UrlValidator();



    // - Controller initialization Method --------
    public void initialize() {
        engine = webView.getEngine();
        engine.load(url);
    }


    // - FXML Methods ------------
    @FXML
    public void navigate(Event ev) {
        System.out.println("'Omnibar' Was Activated");
        String contents = "";
        // boolean valid = urlValidator.isValid(contents);
        boolean valid = true;
        try {
            contents = omnibar.getText();
            URL temp = new URL(contents);
            URLConnection conn = temp.openConnection();
            conn.connect();
        } catch (MalformedURLException ex) {
            System.out.println("\nBad URL Entered <MalformedURLException> ("+contents+")\n");
            valid = false;
        } catch (IOException ex) {
            System.out.println("\nBad URL Entered <IOException> ("+contents+")\n");
            valid = false;
        } catch (Exception ex) {
            System.out.printf("\nBad Input <%s> ("+contents+")\n\n", ex.toString());
            valid = false;
        }
        if (valid) {
            url = contents;
            engine.load(url);
        }
    }


    @FXML
    public void back(Event ev) {
        System.out.println("'Back' Button Pressed");
    }


    @FXML
    public void forward(Event ev) {
        System.out.println("'Forward' Button Pressed");
    }


    @FXML
    public void refresh(Event ev) {
        System.out.println("'Refresh' Button Pressed");
    }


    @FXML
    public void bookmark(Event ev) {
        System.out.println("'Bookmark' Button Pressed");
    }



    // - Controller Methods --------
    public WebView getWebView() { return webView;}
    public VBox getRoot() { return tabRoot;}

}   