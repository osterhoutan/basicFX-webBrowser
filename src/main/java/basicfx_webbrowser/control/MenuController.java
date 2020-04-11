package basicfx_webbrowser.control;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
// import basicfx_webbrowser.chat.*;
// - javafx imports ----
import javafx.scene.layout.HBox;


/**
 * FXML Controller class for the {@code menu.fxml} ({@code basicfx_webbrowser/fxml/menu/menu.fxml}) file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class MenuController extends FXML_Controller {

    // - FXML Objects --------
    @FXML private HBox menuPane;
    @FXML public TabPane toolTabPane;
    @FXML private Tab bookmarkToolTab;
    @FXML private Tab HistoryToolTab;
    @FXML private Tab SettingsToolTab;


    // - Controller initialization Method --------
    public void initialize() {
        toolTabPane.setSide(Side.LEFT);
    }


    // - Controller Methods --------

}