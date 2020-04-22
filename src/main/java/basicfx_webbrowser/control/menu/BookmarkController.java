package basicfx_webbrowser.control.menu;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.control.FXML_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    @FXML private VBox tabRoot;
    @FXML private TitledPane editBMrkMenuTitle;
    @FXML private ListView<String> listView;
    @FXML private TextField nameField;
    @FXML private TextField urlField;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private Button btnDelete;
    @FXML private Button btnNewBookmark;
    @FXML private Button btnEditBookmark;
    @FXML private Button btnRefreshBookmarks;
    @FXML private Button btnOpenBookmark;

    // - Controller Attributes ------------
    private ObservableList<String> bookmarkList;
    private SelectionModel<String> selection;
    private boolean editMode;
    private HBox collapsed = new HBox();

    // - Initializer Method ----------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.bookmarkGUI = this;
        setFormDisabledProperty(true);
        updateBookmarkList();
        selection = listView.getSelectionModel();
        selection.selectedItemProperty().addListener((ob, ov, nv) -> {
            if (nv != null) {
                nameField.setText(nv);
                urlField.setText(Global.bookmarks.getURL(nv));
            } else {
                nameField.setText("Bookmark Name");
                urlField.setText("Bookmark URL"); 
            }
        });
        selection.selectedIndexProperty().addListener((ob, ov, nv) -> {
            boolean value = nv.intValue()<0;    
            btnDelete.setDisable(value);
            btnEditBookmark.setDisable(value);
            btnOpenBookmark.setDisable(value);
        });
    }


    // - FXML loaded Methods -----------

    @FXML
    public void saveChanges(Event ev) {
        setFormDisabledProperty(true);
        selection.clearSelection();
        String name = nameField.getText();
        String url = urlField.getText();
        String selected = selection.getSelectedItem();
        if (editMode) {
            Global.bookmarks.updateBookmark(selected, name, url);
        } else
            Global.bookmarks.addBookmark(name, url);

        editMode = false;
        updateBookmarkList();
        try { 
            Global.bookmarks.write(); 
        } catch (Exception ex) { 
            System.err.println("\n\n\tERROR: Failed to save bookmarks after saving changes (BookmarkController: 98)\n\n");
            ex.printStackTrace(System.err);
        }
    }


    @FXML
    public void cancelChanges(Event ev) {
        setFormDisabledProperty(true);
        editMode = false;
        updateBookmarkList();
        selection.clearSelection();
    }


    @FXML
    public void removeBookmark(Event ev) {
        String selected = selection.getSelectedItem();
        Global.bookmarks.removeBookmark(selected);
        selection.clearSelection();
        updateBookmarkList();
    }
    
    @FXML
    public void addBookmark(Event ev) {
        selection.clearSelection();
        nameField.setText("");
        urlField.setText("");
        setFormDisabledProperty(false);
        editBMrkMenuTitle.setExpanded(true);
        nameField.setText("Bookmark Name");
        nameField.requestFocus();
    }

    @FXML
    public void editBookmark(Event ev) {
        setFormDisabledProperty(false);
        editBMrkMenuTitle.setExpanded(true);
        editMode = true;
    }


    @FXML
    public void refreshBookmarks(Event ev) {
        updateBookmarkList();
        selection.clearSelection();
    }

    @FXML
    public void openBookmark(Event ev) {
        Global.browserGUI.newTab(Global.bookmarks.getURL(selection.getSelectedItem()));
        selection.clearSelection();
    }

    @FXML
    public void itemClicked(MouseEvent mev) {
        if (mev.getClickCount() >= 2) {
            String selected = selection.getSelectedItem();
            // System.out.printf("Item (%s) was Selected\n", selected);    // DEBUG: check double click behavior
            Global.browserGUI.newTab(Global.bookmarks.getURL(selected));
            selection.clearSelection();
         }  
    }


    // - Public Controller Methods ------------
    public void startAddingBookmark(String url) {
        selection.clearSelection();
        urlField.setText(url);
        nameField.setText("");
        Global.menuGUI.openMenu(tab);
        setFormDisabledProperty(false);
        editBMrkMenuTitle.setExpanded(true);
        // nameField.setText("");
        nameField.requestFocus();
        // urlField.setText(url);
    }


    public void setMenuState() {
        if (Global.settings.getMenuState())
            // - Collapse Menu ----
            tab.setContent(collapsed);
        else
            // - Expand Menu ----
            tab.setContent(tabRoot);
    }



    // - Private Controller Methods ------------
    private void updateBookmarkList() {
        bookmarkList = FXCollections.observableList(new ArrayList<String>(Global.bookmarks.getBookmarkNames()));
        bookmarkList.sort((fv, sv) -> fv.compareTo(sv));
        listView.setItems(bookmarkList);
    }

    private void setFormDisabledProperty(boolean value) {
        nameField.setDisable(value);
        urlField.setDisable(value);
        btnSave.setDisable(value);
        btnCancel.setDisable(value);
    }
}