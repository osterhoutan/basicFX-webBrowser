package basicfx_webbrowser.control.menu;

import java.beans.EventHandler;

import basicfx_webbrowser.control.FXML_Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class for the {@code menu/bookmarks.fxml} file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class BookmarkController extends FXML_Controller {
    
    // - FXML loaded Attributes ------------
    @FXML private Tab tab;
    @FXML private VBox bMrkRoot;
    @FXML private TitledPane editBMrkMenuTitle;
    @FXML private ListView<String> listView;
    @FXML private TextField nameField;
    @FXML private TextField urlField;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    // - Initializer Method ----------
    @FXML
    public void initialize() {
        
    }


    // - FXML loaded Methods ------------
    @FXML
    public void saveChanges(Event ev) {

    }


    @FXML
    public void cancelChanges(Event ev) {

    }

    @FXML
    public void itemClicked(EventHandler ev) {
        System.out.print("Item was Selected");
    }
}