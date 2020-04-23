package basicfx_webbrowser.control.menu;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.browser.HistoryEntry;
import basicfx_webbrowser.control.FXML_Controller;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class for the {@code menu/history.fxml} file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class HistoryController extends FXML_Controller {
    
    // - FXML loaded Attributes ------------
    @FXML private Tab tab;
    @FXML private VBox tabRoot;
    @FXML private Button btnDelete;
    @FXML private Button btnClear;
    @FXML private Button btnOpen;
    @FXML private Button btnRefresh;
    @FXML private ListView<JSONObject> listView;


    // - Controller Attributes -----------
    private SelectionModel<JSONObject> selection;
    private HBox collapsed = new HBox();


    // - Initializer Method ----------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.historyGUI = this;
        updateHistory();
        selection = listView.getSelectionModel();

        selection.selectedIndexProperty().addListener((ob, ov, nv) -> btnDelete.setDisable(nv.intValue()<0));

        listView.setCellFactory(param -> new ListCell<JSONObject>() {
            @Override
            protected void updateItem(JSONObject item, boolean empty) {
                super.updateItem(item, empty);
        
                if (empty || item == null || item.get("url") == null || item.get("time")==null) {
                    setText(null);
                } else {
                    setText(String.format("%s\n    %s", item.get("time"), item.get("url")));
                }
            }
        });
    }


    // - FXML loaded Methods ------------
    @FXML 
    public void deleteEntry(Event ev) {
        JSONObject selected = selection.getSelectedItem();
        Global.history.remove((HistoryEntry) selected);
    }

    @FXML
    public void clearAllEntries(Event ev) {
        Global.history.clear();
    }

    @FXML
    public void openEntry(Event ev) {
        Global.browserGUI.newTab((String) selection.getSelectedItem().get("url"));
    }

    @FXML
    public void refreshEntries(Event ev) {
        updateHistory();
    }


    @FXML
    public void itemClicked(MouseEvent mev) {
        if (mev.getClickCount() >= 2) {
            JSONObject selected = selection.getSelectedItem();
            System.err.printf("Item (%s) was Selected\n", selected.get("time") +" -> "+ selected.get("url"));    // DEBUG: check double click behavior
            Global.browserGUI.newTab((String) selected.get("url"));
         }  
    }


    // - public controller methods ------------
    public void updateHistory() {
        listView.setItems(FXCollections.observableArrayList(Global.history.getHistory()));
    }

    public void setMenuState() {
        if (Global.settings.getMenuState())
            // - Collapse Menu ----
            tab.setContent(collapsed);
        else
            // - Expand Menu ----
            tab.setContent(tabRoot);
    }
}