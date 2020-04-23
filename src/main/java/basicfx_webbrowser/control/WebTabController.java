package basicfx_webbrowser.control;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.browser.SessionEntry;
import basicfx_webbrowser.myfx.OmniBar;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML private Tab tab;
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
    private WebEngine engine;
    private WebHistory history;
    private Worker<Void> loadWorker;
    // private boolean loading = true;
    // private UrlValidator urlValidator = new UrlValidator();



    // - Controller initialization Method --------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();
        history = engine.getHistory();
        loadWorker = engine.getLoadWorker();
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
        loadWorker.stateProperty().addListener((ob, ov, nv) -> loadingStateChange(nv));
        
        // - set up Current Web page Listener ------
        engine.locationProperty().addListener((ob, ov, nv) -> {
            // - update Global History ------
            Global.history.addHistory(nv);
            try { Global.historyGUI.updateHistory(); } catch (Exception ex) {System.err.println("initialization sluggishness");}
            // - Update the Browser Bar ------
            omnibar.setText(nv);
            checkBmkState(nv);
            checkNavState();
            // - Update the Browser Tab ------
            setTitle(engine.getTitle(), nv);
        });
    }


    // - FXML Methods ------------
    @FXML
    public void navigate(Event ev) {
        // System.err.println("'Omnibar' Was Activated");  // DEBUG: check to see if activates
        String input = omnibar.getURL();
        if (input==null) input = Global.settings.getErrorPage().toExternalForm();
        navigate(input);
    }


    @FXML
    public void back(Event ev) {
        // System.err.println("'Back' Button Pressed");    // DEBUG: check to see if activates
        history.go(-1);
    }


    @FXML
    public void forward(Event ev) {
        // System.err.println("'Forward' Button Pressed"); // DEBUG: check to see if activates
        history.go(1);
    }


    @FXML
    public void refresh(Event ev) {
        // System.err.println("'Refresh' Button Pressed"); // DEBUG: check to see if activates
        engine.reload();
    }


    @FXML
    public void bookmark(Event ev) {
        // System.err.println("'Bookmark' Button Pressed");    // DEBUG: check to see if activates
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

    @FXML
    public void closeTab() {
        // - remove tab From the session ------
        // int id = Global.browserGUI.tabControllers.indexOf(this);
        // Global.session.remove(id);
        Global.browserGUI.tabControllers.remove(this);
        // - close application if that was the last Tab ------
        if (Global.browserGUI.tabControllers.size()<1) Platform.exit();
    }



    // - Public Controller Methods --------
    public WebView getWebView() { return webView;}
    public BorderPane getRoot() { return tabRoot;}


    public void restoreTab(SessionEntry entry) {
        history.getEntries().setAll(entry.history);
        history.go(entry.currentIndex-history.getCurrentIndex());
    }


    public void navigate(String url) {
        engine.load(url);
        Global.history.addHistory(url);
        checkBmkState(url);
        checkNavState();
        setTitle(engine.getTitle(), url);
    }

    public void newTab() {
        String url = Global.settings.getStartPage();
        engine.load(url);
        Global.history.addHistory(url);
        checkBmkState(url);
        checkNavState();
        setTitle("New Tab: ", "default");
        omnibar.setText("New Tab");
    }

    public int addToSession() {
        return Global.session.addTab(new SessionEntry(history));
    }


    // - Private Controller Methods ------
    public String getCurrentURL() {
        return engine.getLocation().replaceFirst("#.*$", "");
    }


    private void checkBmkState(String url) {
        if (Global.bookmarks.isBookmark(url))
            bookmarkBtn.setText("\u2605");
        else
            bookmarkBtn.setText("\u2606");
    }


    private void setTitle(String title, String url) {
        try {
            String pageIconUrl = String.format("http://www.google.com/s2/favicons?domain_url=%s", URLEncoder.encode(url, "UTF-8"));
            if (title != null)
                tab.setText((title.length()<24) ? title+" " : title.substring(0, 24)+"... ");
            else
                tab.setText((url.length()<24) ? url+" " : url.substring(0, 24)+"... ");
            // - update the page Icon ----
            Image pageIcon = new Image(pageIconUrl, true);
            ImageView iv = new ImageView(pageIcon);
            tab.setGraphic(iv);
        } catch (UnsupportedEncodingException ex) {
            System.err.println("\n\tERROR: Failed to encode the url: \""+url+"\" for use in getting the page Icon\n");
            ex.printStackTrace(System.err);
        }
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
                navigate(Global.settings.getErrorPage().toExternalForm());
                setTitle("Error:  ", "default");
                omnibar.setText("Error Page");
                break;
            }
            case CANCELLED: {
                refreshBtn.setText("\u27f3");
                omnibar.setDisable(false);
                bookmarkBtn.setDisable(false);
                checkBmkState(getCurrentURL());
                break;
            }
        }
    }

}   