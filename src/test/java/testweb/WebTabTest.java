package testweb;

import java.net.URL;

import basicfx_webbrowser.Global;
import basicfx_webbrowser.control.WebTabController;
// import basicfx_webbrowser.chat.*;
// - javafx imports ----
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Create a web browsing GUI application.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Date:</b> 2020/04/21 <i>(due date)</i>
 * @version <b>Project:</b> USU-Sp2020: CS-2410 GUI's -> Final Project: basicFX-webBrowser -> BasicFX_webBrowser main application Class
 */
public class WebTabTest extends Application {

    public static void main(String[] args) {
        try{
            launch(args);
        // } catch (JustCloseDown jcd) { System.out.println("\n\nClosing Application\n"); // System.exit(0);
        } catch (Exception ex) { System.out.printf("\n%s\n", ex.getMessage()); ex.printStackTrace(); }
        System.exit(0);
    }

    private Scene scene;
    private StackPane root = new StackPane();
    private AnchorPane popUpPane = new AnchorPane();


    @Override
    public void start(Stage mainStage) throws Exception {
        // - Name the Scene ----
        mainStage.setTitle("basicFX-webBrowser: (Web Tab Test) - a02201315");

        

        // - Initialize the browser Backend -------
        initializeBackend();

        // - Load the JavaFXML ----
        URL mainFXML = getClass().getResource("/fxml/tab.fxml");
        // System.out.printf("\n\n\t%s\n\n\n", mainFXML); 
        FXMLLoader loader = new FXMLLoader(mainFXML);
        Parent tabRoot = loader.load();
        WebTabController tab = loader.<WebTabController>getController();
        tab.setPopUpPane(popUpPane);
        // root.setStyle("-fx-background-color: null;");
        // root.setPickOnBounds(true);
        // root.setOpacity(0);
        // popUpPane.setStyle("-fx-background-color: null;");
        // popUpPane.setOpacity(0);
        // popUpPane.setPickOnBounds(true);
        // tabRoot.setOpacity(1);

        // - create the scene --------
        // root.getChildren().addAll(tabRoot, popUpPane);
        scene = new Scene(tabRoot, 1024, 1024);

        // - Load the css Files ----
        // scene.getStylesheets().add(getClass().getResource(Global.settings.getTheme()).toExternalForm());

        // - Finish Creating Interface ----
        mainStage.setScene(scene);

        // - Additional Action ----
        // tab.callMeAfter();

        // - Display the GUI ----
        mainStage.show();
    }


    private void initializeBackend() throws Exception {
        Global.settings.setFile(Global.appDataDir+"/config/settings.json");
        Global.bookmarks.setFile(Global.appDataDir+("/config/bookmarks.json"));
        Global.history.setFile(Global.appDataDir+("/AppData/history.json"));
        Global.session.setFile(Global.appDataDir+("/AppData/session.json"));
        Global.settings.read();
        Global.bookmarks.read();
        Global.history.read();
        if (Global.settings.doRestore()) Global.session.read();
    }

    @Override
    public void stop() throws Exception {
        Global.settings.write();
        Global.bookmarks.write();
        Global.history.write();
        Global.session.write();
        System.out.println("\nStage is closing");     // DEBUG: test to see if called on close
        throw new JustCloseDown();
        // System.exit(0);
    }

    private class JustCloseDown extends Exception {
        public static final long serialVersionUID = 256256256l;
    }


}