package basicfx_webbrowser;

import java.io.FileInputStream;
import java.net.URL;

import basicfx_webbrowser.browser.BookmarkManager;
import basicfx_webbrowser.browser.HistoryManager;
import basicfx_webbrowser.browser.SessionManager;
import basicfx_webbrowser.browser.SettingsManager;
// import basicfx_webbrowser.chat.*;
// - javafx imports ----
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Create a web browsing GUI application.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Date:</b> 2020/04/21 <i>(due date)</i>
 * @version <b>Project:</b> USU-Sp2020: CS-2410 GUI's -> Final Project: basicFX-webBrowser -> BasicFX_webBrowser main application Class
 */
public class BasicFX_webBrowser extends Application {

    public static void main(String[] args) {
        try{
            launch(args);
        } catch (Exception ex) { System.out.printf("\n%s\n", ex.getMessage()); ex.printStackTrace(); }
    }

    private Scene scene;
    private SessionManager session;
    private HistoryManager history;
    private BookmarkManager bookmarks;
    private SettingsManager settings;


    @Override
    public void start(Stage mainStage) throws Exception {
        // - Name the Scene ----
        mainStage.setTitle("basicFX-webBrowser - a02201315");

        

        // - Initialize the browser Backend -------
        initializeBackend();

        // - Load the JavaFXML ----
        URL mainFXML = getClass().getResource("/fxml/main.fxml");
        // System.out.printf("\n\n\t%s\n\n\n", mainFXML); 
        Parent root = FXMLLoader.load(mainFXML);

        // - create the scene --------
        scene = new Scene(root, 1024, 1024);

        // - Load the css Files ----
        scene.getStylesheets().add(getClass().getResource(settings.getTheme()).toExternalForm());

        // - Finish Creating Interface ----
        mainStage.setScene(scene);

        // - Additional Action ----


        // - Display the GUI ----
        mainStage.show();
    }


    private void initializeBackend() throws Exception {
        Global.settings.setFile(getClass().getResource("/config/settings.json"));
        Global.bookmarks.setFile(getClass().getResource("/config/bookmarks.json"));
        Global.history.setFile(getClass().getResource("/history/history.json"));
        Global.session.setFile(getClass().getResource("/history/session.json"));
        settings.read();
        bookmarks.read();
        history.read();
        if (settings.doRestore()) session.read();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stage is closing");
        settings.write();
        bookmarks.write();
        history.write();
        session.write();
    }


}