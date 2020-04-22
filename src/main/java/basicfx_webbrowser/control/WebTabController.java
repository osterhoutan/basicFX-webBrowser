package basicfx_webbrowser.control;

import java.net.URL;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;

// import org.apache.commons.validator.UrlValidator;

import basicfx_webbrowser.myfx.browser.tab.OmniBar;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 * FXML Controller class for the {@code tab.fxml} ({@code basicfx_webbrowser/fxml/browser/tab/tab.fxml}) file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class WebTabController extends FXML_Controller {

    // - FXML Objects --------
    @FXML private BorderPane tabRoot;
    @FXML private VBox webBar;
    @FXML private HBox browserBar;
    @FXML private Button backBtn;
    @FXML private Button fwdBtn;
    @FXML private Button refreshBtn;
    @FXML private Button bookmarkBtn;
    @FXML private OmniBar omnibar;
    @FXML private WebView webView;


    // - Controller Attributes --------
    private AnchorPane popUpPane;
    private WebEngine engine;
    private WebHistory history;
    private String homeURL = Global.settings.getStartPage();
    // private boolean loading = true;
    // private UrlValidator urlValidator = new UrlValidator();



    // - Controller initialization Method --------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();
        history = engine.getHistory();
        engine.setUserAgent(Global.webAgentName);

        // - define bindings ------------
        omnibar.minWidthProperty().bind(tabRoot.widthProperty().subtract(
                    backBtn.widthProperty().add(fwdBtn.widthProperty().add(
                        refreshBtn.widthProperty().add(bookmarkBtn.widthProperty().add(16))
                    ))
                ));
        browserBar.maxWidthProperty().bind(webBar.widthProperty());
        // webView.maxWidthProperty().bind(browserBar.widthProperty().add(4));

        // - set up load listener ----------
        engine.getLoadWorker().stateProperty().addListener((ob, ov, nv) -> loadingStateChange(nv));
        
        // - set up Current Web page Listener ------
        engine.locationProperty().addListener((ob, ov, nv) -> {
            Global.history.addHistory(nv);
            try { Global.historyGUI.updateHistory(); } catch (Exception ex) {System.err.println("initialization sluggishness");}
            omnibar.setText(nv);
            checkBmkState(nv);
            checkNavState();
        });
    }


    // - FXML Methods ------------
    // @FXML
    public void navigate(Event ev) {
        // System.out.println("'Omnibar' Was Activated");  // DEBUG: check to see if activates
        String input = omnibar.getURL();
        if (input==null) input = Global.settings.getErrorPage();
        engine.load(input);
    }


    @FXML
    public void back(Event ev) {
        // System.out.println("'Back' Button Pressed");    // DEBUG: check to see if activates
        history.go(-1);
    }


    @FXML
    public void forward(Event ev) {
        // System.out.println("'Forward' Button Pressed"); // DEBUG: check to see if activates
        history.go(1);
    }


    @FXML
    public void refresh(Event ev) {
        // System.out.println("'Refresh' Button Pressed"); // DEBUG: check to see if activates
        engine.reload();
    }


    @FXML
    public void bookmark(Event ev) {
        // System.out.println("'Bookmark' Button Pressed");    // DEBUG: check to see if activates
        String url = getCurrentURL();
        // - if page is already bookmarked ----
        if (Global.bookmarks.isBookmark(url))
            Global.bookmarks.removeBookmarkByURL(url);
        // - if page is not already bookmarked ----
        else 
            Global.bookmarkGUI.startAddingBookmark(url);

        // - refresh the state of the bookmark button ----
        Global.bookmarkGUI.refreshBookmarks(new ActionEvent());
        // try { Thread.sleep(50); } catch (Exception ex) { System.err.println("Thread failed to sleep (WebTabController: 126)"); }
        checkBmkState(url);
        // try { Thread.sleep(60_000L); } catch (Exception ex) { System.err.println("Thread failed to sleep (WebTabController: 129)"); }
        checkBmkState(url);
        Global.bookmarkGUI.refreshBookmarks(new ActionEvent());
    }



    // - Public Controller Methods --------
    public WebView getWebView() { return webView;}
    public BorderPane getRoot() { return tabRoot;}
    public void setPopUpPane(AnchorPane popUpPane) { this.popUpPane = popUpPane; }
    public ReadOnlyStringProperty getPageTitle() { 
        // try {
        //     while (engine.titleProperty().getValue()==null) Thread.sleep(100);
        // } catch (Exception ex) {System.out.print("Thread failed to wait (TabController: 139)\n");}
        return engine.titleProperty(); 
    }

    public void loadPage(String url) {
        engine.load(url);
    }


    // - Private Controller Methods ------
    public String getCurrentURL() {
        return engine.getLocation().replaceFirst("#.*$", "");
    }

    private void navigate(String url) {
        engine.load(url);
        Global.history.addHistory(url);
        checkBmkState(url);
        checkNavState();
    }


    private void checkBmkState(String url) {
        if (Global.bookmarks.isBookmark(url))
            bookmarkBtn.setText("\u2605");
        else
            bookmarkBtn.setText("\u2606");
    }

    private void checkNavState() {
        int current = history.getCurrentIndex();
        backBtn.setDisable((current == 0));
        fwdBtn.setDisable((current >= history.getEntries().size()-1));
    }


    private void loadingStateChange(State state) {
        switch (state) {
            case SUCCEEDED: {

            } case READY: {
                omnibar.setDisable(false);
                bookmarkBtn.setDisable(false);
                refreshBtn.setText("\u27f3");
                checkNavState();
                checkBmkState(getCurrentURL());
                break;
            }
            case RUNNING:
            case SCHEDULED: {
                backBtn.setDisable(true);
                fwdBtn.setDisable(true);
                omnibar.setDisable(true);
                bookmarkBtn.setDisable(true);
                refreshBtn.setText("\u292b");
                break;
            }
            case FAILED: {
                refreshBtn.setText("\u292b");
                omnibar.setDisable(false);
                navigate(Global.settings.getErrorPage());
                checkNavState();
                break;
            }
            case CANCELLED: {
                refreshBtn.setText("\u27f3");
                omnibar.setDisable(false);
                bookmarkBtn.setDisable(false);
                String url = getCurrentURL();
                checkBmkState(url);
                checkNavState();
                break;
            }
        }
    }

}   