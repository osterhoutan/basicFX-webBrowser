package basicfx_webbrowser.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.browser.SessionEntry;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class for the {@code browser.fxml} ({@code basicfx_webbrowser/fxml/browser/browser.fxml}) file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class BrowserController extends FXML_Controller {

    // - FXML Attributes --------
    // @FXML private TabPane browserPane;
    // @FXML public Rectangle spacer;
    @FXML private TabPane browserTabs;
    @FXML private Tab newTab;


    // - Class Attributes --------
    public ArrayList<WebTabController> tabControllers = new ArrayList<>();
    public boolean startup = true;


    // - Controller Initialization Method --------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.browserGUI = this;
        // if (Global.session.getSessionSize()>0) restore();
        startup = false;
        newTab.setDisable(false);
    }


    // - Controller Methods --------
    @FXML
    public void newTab(Event ev) {
        if (!newTab.selectedProperty().get() || startup) return;
        try {
            WebTabController controller = createTab();
            controller.newTab();
            // controller.addToSession();
            tabControllers.add(controller);
        } catch (Exception ex) {System.err.println("Failed to convert tabRoot. (BrowserController.newTab(Event ev): 51)\n\t"+ex.getMessage()); ex.printStackTrace(System.err); }
    } 
    
    
    public void newTab(String url) {
        try {
            WebTabController controller = createTab();
            controller.navigate(url);
            // controller.addToSession();
            tabControllers.add(controller);
        } catch (Exception ex) {System.err.println("Failed to convert tabRoot. (BrowserController.newTab(String url): 61)\n\t"+ex.getMessage()); ex.printStackTrace(System.err); }
    } 

    // public void saveSession() {
    //     Global.session.clearSession();
    //     for (WebTabController tab : tabControllers)
    //         tab.addToSession();
    // }



    // - Private Controller Methods ------------
    private WebTabController createTab() throws Exception {
        browserTabs.getTabs().remove(newTab);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
        Tab tab = loader.<Tab>load();
        WebTabController controller = loader.getController();
        browserTabs.getTabs().add(tab);
        browserTabs.getTabs().add(newTab);
        browserTabs.getSelectionModel().select(tab);
        return controller;
    }

    // private void restore() {
    //     List<SessionEntry> entries = Global.session.asList();
    //     for (int ii=0; ii<entries.size(); ii++) {
    //         try {
    //             WebTabController controller = createTab();
    //             controller.restoreTab(entries.get(ii));
    //             tabControllers.add(ii, controller);
    //         } catch (Exception ex) {System.err.println("Failed to convert tabRoot. (BrowserController.restore(): 92)\n\t"+ex.getMessage()); ex.printStackTrace(System.err); }
    //     }
    // }

}