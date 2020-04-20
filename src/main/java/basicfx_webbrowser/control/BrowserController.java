package basicfx_webbrowser.control;

import java.net.URL;
import java.util.ArrayList;

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
    public void initialize() {
    }


    // - Controller Methods --------
    @FXML
    public void newTab(Event ev) {
        if (!newTab.selectedProperty().get()) return;
        try {
            browserTabs.getTabs().remove(newTab);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
            Tab tab = new Tab();
            BorderPane tabRoot = loader.load(); 
            tab.setContent(tabRoot);
            WebTabController controller = loader.getController();
            tabControllers.add(controller);
            controller.getPageTitle().addListener((ob, ov, nv) -> {
                if (nv != null)
                    tab.setText(nv);
                else
                    tab.setText(controller.getCurrentURL());
            });
            browserTabs.getTabs().add(tab);
            browserTabs.getTabs().add(newTab);
            browserTabs.getSelectionModel().select(tab);
        } catch (Exception ex) {System.out.println("Failed to convert tabRoot. (BrowserController.newTab(Event ev): 58)\n\t"+ex.getMessage()); } //ex.printStackTrace(); }
    } 
    
    
    public void newTab(URL url) {
        
    } 

}