package basicfx_webbrowser.control;

import java.net.URL;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
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
    @FXML private TabPane toolTabPane;
    @FXML private Tab bookmarkToolTab;
    @FXML private Tab HistoryToolTab;
    @FXML private Tab SettingsToolTab;

    // - Controller Attributes ------------
    private SingleSelectionModel<Tab> selection;
    private int prev;


    // - Controller initialization Method --------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.menuGUI = this;
        selection = toolTabPane.getSelectionModel();
        setMenuState();
    }


    // - Controller Node Event Methods --------
    @FXML
    public void clicked(MouseEvent mev) {
        if (mev.getClickCount() >= 2 || Global.settings.getMenuState()) {
            // System.out.println("Menu Toggle Activated");    // DEBUG: check double click behavior
            Global.settings.toggleMenuState();
            setMenuState();
         }  
    }


    // - Public Controller Methods ------------
    public void openMenu() {
        Global.settings.setMenuState(false);
        setMenuState();
    }

    public void openMenu(Tab tab) {
        openMenu();
        selection.select(tab);
    }

    public void closeMenu() {
        Global.settings.setMenuState(true);
        setMenuState();
    }


    // - Private Controller Methods -----------
    private void setMenuState() {
        Global.bookmarkGUI.setMenuState();
        Global.historyGUI.setMenuState();
        Global.settingsGUI.setMenuState();
        prev = (!Global.settings.getMenuState()) ? selection.getSelectedIndex() : prev;
        selection.selectFirst();
        try { Thread.sleep(50); } catch (Exception ex) { System.err.println("Thread failed to rest (MenuController: 66)"); }
        selection.selectLast();
        try { Thread.sleep(50); } catch (Exception ex) { System.err.println("Thread failed to rest (MenuController: 66)"); }
        if (!Global.settings.getMenuState()) selection.select(prev);
        else selection.clearSelection();
    }

}