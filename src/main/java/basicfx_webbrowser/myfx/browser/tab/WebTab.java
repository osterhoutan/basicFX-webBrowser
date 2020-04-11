// package basicfx_webbrowser.myfx.browser.tab;

// import java.net.URL;

// import basicfx_webbrowser.control.WebTab;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.control.Tab;
// import javafx.scene.web.WebEngine;
// import javafx.scene.web.WebView;


// /**
//  * The Browser Tab for the application.
//  * 
//  * @author Andrew Osterhout (a02201315)
//  * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs -> Final Project: basicFX_webBrowser
//  */
// public class WebTab extends Tab {

//     private String url;
//     private WebView webView;
//     private WebEngine engine;
//     private WebTab tab;

//     // - Class Constructor ---------
//     public WebTab() {   
//         super();     
//         url = "http://google.com"; //"File:///html/newtab.html";
//         initialize();
//     }

//     public WebTab(String url) {
//         super();
//         this.url = url;
//         initialize();
//     }

//     // - Class Methods --------
//     private void initialize() {
//         FXMLLoader loader = new FXMLLoader();
//         try {loader.setLocation(new URL(new URL("File:"), "./fxml/tab.fxml"));
//         } catch (Exception ex) {
//             System.err.printf("\n\tERROR: basicfx_webbrowser.myfx.browser.tab.WebTab.initialize(): %s\n%s\n",
//                                 ex.getCause(), ex.getMessage());
//             ex.printStackTrace(System.err); System.err.println();
//         }
//         tab = loader.getController();
//         webView = tab.getWebView();
//         engine = webView.getEngine();
//         engine.load(url);
//         this.setContent(tab.getRoot());
//     }

// }