package basicfx_webbrowser.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

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
    @FXML public TabPane browserTabs;
    @FXML public Tab newTab;


    // - Class Attributes --------
    public ArrayList<WebTabController> tabControllers = new ArrayList<>();


    // - Controller Initialization Method --------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.browserGUI = this;
    }


    // - Controller Methods --------
    @FXML
    public void newTab(Event ev) {
        if (!newTab.selectedProperty().get()) return;
        newTab(Global.settings.getStartPage());
    } 
    
    
    public void newTab(String url) {
        try {
            browserTabs.getTabs().remove(newTab);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
            Tab tab = new Tab();
            BorderPane tabRoot = loader.load(); 
            tab.setContent(tabRoot);
            tab.setOnClosed((ev) -> { if (browserTabs.getTabs().size()<=1) Platform.exit(); });
            WebTabController controller = loader.getController();
            tabControllers.add(controller);
            controller.getPageTitle().addListener((ob, ov, nv) -> {
                if (nv != null)
                    tab.setText(nv);
                else
                    tab.setText(controller.getCurrentURL());
            });
            controller.loadPage(url);
            browserTabs.getTabs().add(tab);
            browserTabs.getTabs().add(newTab);
            browserTabs.getSelectionModel().select(tab);
        } catch (Exception ex) {System.out.println("Failed to convert tabRoot. (BrowserController.newTab(String url): 58)\n\t"+ex.getMessage()); } //ex.printStackTrace(); }
    } 

}