package basicfx_webbrowser.control;

// - my code imports ----
import basicfx_webbrowser.myfx.*;
import basicfx_webbrowser.myfx.menu.*;
import basicfx_webbrowser.myfx.browser.*;
import basicfx_webbrowser.browser.*;
// - javafx imports ----
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.beans.property.*;
import javafx.fxml.FXML;


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
    private BookmarkManager bookmarkManager;
    private HistoryManager historyManager;
    private SettingsManager settingsManager;



    // - Controller initialization Method --------
    public void initialize() {
        // - Create the managers ----
        // BorderPane.
        // bookmarkManager = 
        // browser.spacer.widthProperty().bind(menu.toolTabPane.widthProperty());
    }


    // - Controller Methods --------
    


}