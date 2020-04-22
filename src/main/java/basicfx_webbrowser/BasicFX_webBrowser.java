package basicfx_webbrowser;

import java.io.FileInputStream;
import java.io.IOException;
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

    private static final Exception exitException = new Exception("\n\n\tProgram Exiting Normally\n\n");

    public static void main(String[] args) {
        try{
            launch(args);
        } catch (Exception ex) {
            if (ex!=exitException) {
                System.out.printf("\n%s\n", ex.getMessage());
                ex.printStackTrace(); 
            }
            System.exit(0);
        }
    }

    private Scene scene;


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
        scene.getStylesheets().add(Global.settings.getTheme().toExternalForm());

        // - Finish Creating Interface ----
        mainStage.setScene(scene);

        // - Additional Action ----


        // - Display the GUI ----
        mainStage.show();
    }


    private void initializeBackend() throws Exception {
        Global.settings.setFile(Global.appDataDir+"config/settings.json");
        Global.bookmarks.setFile(Global.appDataDir+("config/bookmarks.json"));
        Global.history.setFile(Global.appDataDir+("history.json"));
        Global.session.setFile(Global.appDataDir+("session.json"));
        Global.settings.read();
        Global.bookmarks.read();
        Global.history.read();
        if (Global.settings.doRestore()) Global.session.read();
    }

    @Override
    public void stop() throws Exception {
        // System.out.println("Stage is closing");     // DEBUG: test to see if called on close
        try {
            Global.settings.write();
            Global.bookmarks.write();
            Global.history.write();
            Global.session.write();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        // System.exit(0);
        throw exitException;
    }


}