package basicfx_webbrowser.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
// - javafx imports ----
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 * FXML Controller class for the {@code tab.fxml} ({@code basicfx_webbrowser/fxml/browser/tab/tab.fxml}) file.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
 */
public class WebTabController extends FXML_Controller {

    // - FXML Objects --------
    @FXML private VBox tabRoot;
    @FXML private HBox webBar;
    @FXML private HBox browserBar;
    @FXML private Button backBtn;
    @FXML private Button fwdBtn;
    @FXML private Button refreshBtn;
    @FXML private Button bookmarkBtn;
    @FXML private WebView webView;



    // - Controller initialization Method --------
    public void initialize() {

    }


    // - Controller Methods --------
    public WebView getWebView() { return webView;}
    public VBox getRoot() { return tabRoot;}

}   