package basicfx_webbrowser.control;

// - my code imports ----
import basicfx_webbrowser.myfx.browser.WebTabPane;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
// import basicfx_webbrowser.chat.*;
// - javafx imports ----
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class for the {@code browser.fxml} ({@code basicfx_webbrowser/fxml/browser/browser.fxml}) file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class BrowserController extends FXML_Controller {

    // - FXML Attributes --------
    @FXML private HBox browserPane;
    @FXML public Rectangle spacer;
    @FXML public TabPane webTabs;


    // - Class Attributes --------



    // - Controller Initialization Method --------
    public void initialize() {
        spacer.setWidth(100);
    }


    // - Controller Methods --------
    

}