package basicfx_webbrowser.control;

import java.net.URL;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.browser.BookmarkManager;
import basicfx_webbrowser.browser.HistoryManager;
import basicfx_webbrowser.browser.SettingsManager;
import javafx.fxml.FXML;
// - javafx imports ----
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


/**
 * FXML Controller class for the {@code main.fxml} ({@code basicfx_webbrowser/main.fxml}) file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class MainController extends FXML_Controller {

    // - FXML Objects --------
    @FXML public StackPane root;
    @FXML public BorderPane mainPane;
    @FXML public BrowserController browser;
    @FXML public HBox BrowserPane;
    @FXML public MenuController menu;
    @FXML public HBox MenuPane;
    @FXML public AnchorPane topButtons;


    // - Controller Attributes --------



    // - Controller initialization Method --------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.appGUI = this;
        // - Create the managers ----
        // BorderPane.
        // bookmarkManager = 
        // browser.spacer.widthProperty().bind(menu.toolTabPane.widthProperty());
    }


    // - Controller Methods --------
    


}