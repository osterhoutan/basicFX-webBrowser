package basicfx_webbrowser.control.menu;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.browser.settings.SearchEngine;
import basicfx_webbrowser.control.FXML_Controller;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class for the {@code menu/settings.fxml} file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class SettingsController extends FXML_Controller {

    // - FXML loaded Attributes ------------
    @FXML private Tab tab;
    @FXML private VBox tabRoot;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private VBox generalSettingsPane;
    @FXML private CheckBox chkResumeSession;
    @FXML private RadioButton rdoUseNewTab;
    @FXML private RadioButton rdoUseHomePage;
    @FXML private TextField homePageField;
    @FXML private Label lblBadHomeUrl;
    @FXML private ChoiceBox<String> chsBxTheme; 
    @FXML private ChoiceBox<String> chsBxSearchEngine;
    // @FXML private TextField nameField;
    @FXML private TextField headerField;
    @FXML private Label lblBadHeader;
    @FXML private TextField urlField;
    @FXML private Label lblBadQueryURL;


    // - Controller Attributes ------------
    private HBox collapsed = new HBox();
    private SimpleBooleanProperty unsavedChanges = new SimpleBooleanProperty(false);
    private SelectionModel<String> themeSelection;
    private SelectionModel<String> searchSelection;
    private ToggleGroup tgNewTab = new ToggleGroup();
    

    // - Initializer Method ----------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rdoUseNewTab.setToggleGroup(tgNewTab);
        rdoUseHomePage.setToggleGroup(tgNewTab);
        themeSelection = chsBxTheme.getSelectionModel();
        searchSelection = chsBxSearchEngine.getSelectionModel();
        Global.settingsGUI = this;
        unsavedChanges.addListener((ob, ov, nv) -> {
            btnSave.setDisable(!nv);
            btnCancel.setDisable(!nv); 
        });

        homePageField.textProperty().addListener((ob, ov, nv) -> {
            unsavedChanges.set(true);
            if (!nv.matches(Global.urlRegEx))
                lblBadHomeUrl.setText("     ! Invalid URL !");
            else
                lblBadHomeUrl.setText("");
        });

        headerField.textProperty().addListener((ob, ov, nv) -> {
            unsavedChanges.set(true);
            if (!nv.matches("^[a-z]{3,16}:$") && nv!="")
                lblBadHeader.setText("! Invalid Header !");
            else
                lblBadHeader.setText("");
        });

        urlField.textProperty().addListener((ob, ov, nv) -> {
            unsavedChanges.set(true);
            if (!nv.matches(Global.urlRegEx) && !nv.matches("^.{16,}%s.*$"))
                lblBadQueryURL.setText("! Invalid Query URL !");
            else
                lblBadQueryURL.setText("");
        });

        updateSettingsGUI();

        chkResumeSession.selectedProperty().addListener((ob, ov, nv) -> unsavedChanges.set(true));
        themeSelection.selectedItemProperty().addListener((ob, ov, nv) -> unsavedChanges.set(true));
        searchSelection.selectedItemProperty().addListener((ob, ov, nv) -> unsavedChanges.set(true));
        // nameField.textProperty().addListener((ob, ov, nv) -> unsavedChanges.set(true));
        tgNewTab.selectedToggleProperty().addListener((ob, ov, nv) -> {
            unsavedChanges.set(true);
            homePageField.setDisable(nv==rdoUseNewTab);
        });
        unsavedChanges.set(false);
    }


    // - FXML loaded Methods ------------
    @FXML
    public void saveChanges(Event ev) {
        // - get validate and Set Values -------
        Global.settings.setDoRestore(chkResumeSession.selectedProperty().getValue());
        Global.settings.setNewTabHome(tgNewTab.getSelectedToggle().equals(rdoUseNewTab));
        Global.settings.setHomePage(homePageField.getText());
        Global.settings.setTheme(themeSelection.getSelectedItem());
        Global.settings.setSearchEngine(searchSelection.getSelectedItem());
        Global.settings.setCustomSearchEngine(
                            headerField.getText(),
                            urlField.getText());

        updateSettingsGUI();
        unsavedChanges.set(false);
        try { 
            Global.bookmarks.write(); 
        } catch (Exception ex) { 
            System.err.println("\n\n\tERROR: Failed to save bookmarks after saving changes (BookmarkController: 98)\n\n");
            ex.printStackTrace(System.err);
        }
    }


    @FXML
    public void cancelChanges(Event ev) {
        updateSettingsGUI();
        unsavedChanges.set(false);
    }

    @FXML
    public void openSettingsFile(Event ev) {
        try {
            Runtime.getRuntime().exec('"'+Global.settings.getFilePath()+'"');
        } catch (Exception ex) {
            final ClipboardContent content = new ClipboardContent();
            content.put(DataFormat.PLAIN_TEXT, Global.settings.getFilePath());
            System.err.println("\n\n\tERROR: Failed to open the Settings file (`"+content.getString()+"`) on this system\n\n\t\tCopied the contents to your system clipboard instead.\n\n");
            ex.printStackTrace(System.err);
            Clipboard.getSystemClipboard().setContent(content);
        }
    }

    @FXML
    public void openAppData(Event ev) {
        final ClipboardContent content = new ClipboardContent();
        content.put(DataFormat.PLAIN_TEXT, (new File(Global.appDataDir)).getAbsolutePath());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @FXML
    public void openSettingsHelpPage(Event ev) {
        Global.browserGUI.newTab("https://github.com/osterhoutan/basicFX-webBrowser/blob/master/doc/guid/settingsConfig.md#settings-config-user-guid");
    }

    @FXML
    public void openCustomHelpPage(Event ev) {
        Global.browserGUI.newTab("https://github.com/osterhoutan/basicFX-webBrowser/blob/master/doc/guid/userAssets.md#user-assets-guid");
    }


    // - Public Controller Methods ------------
    public void setMenuState() {
        if (Global.settings.getMenuState())
            // - Collapse Menu ----
            tab.setContent(collapsed);
        else
            // - Expand Menu ----
            tab.setContent(tabRoot);
    }

    // - Private Controller Methods ------------
    private void updateSettingsGUI() {
        chkResumeSession.setSelected(Global.settings.doRestore());
        tgNewTab.selectToggle((Global.settings.isNewTabHome()) ? rdoUseNewTab : rdoUseHomePage);
        homePageField.setText(Global.settings.getHomePage());
        chsBxTheme.setItems(FXCollections.observableList(Global.settings.getThemeNameList()));
        themeSelection.select(Global.settings.getThemeName());
        chsBxSearchEngine.setItems(FXCollections.observableList(Global.settings.getSearchEngineList()));
        searchSelection.select(Global.settings.getSearchEngine().getName());
        SearchEngine custom = Global.settings.getCustomSearchEngine();
        headerField.setText(custom.getSearchHeader());
        urlField.setText(custom.getQueryURL());
    }

    
    
    
    
}