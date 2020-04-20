package basicfx_webbrowser.control.menu;

import basicfx_webbrowser.control.FXML_Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class for the {@code menu/history.fxml} file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class HistoryController extends FXML_Controller {
    
    // - FXML loaded Attributes ------------
    @FXML private Tab tab;
    @FXML private Button btnDelete;
    @FXML private ListView<String> lstView;

    // - Initializer Method ----------
    @FXML
    public void initialize() {

    }


    // - FXML loaded Methods ------------
    @FXML 
    public void deleteEntry(Event ev) {

    }


    @FXML
    public void itemClicked(ListView.EditEvent<String> ev) {
        System.out.print("Item was Selected");
    }
}