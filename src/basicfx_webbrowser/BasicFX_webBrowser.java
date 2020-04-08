package basicfx_webbrowser;

// - Import Statements --------
// - my code imports ----
import basicfx_webbrowser.myfx.*;
import basicfx_webbrowser.fxml.*;
import basicfx_webbrowser.browser.*;
import basicfx_webbrowser.chat.*;
// - javafx imports ----
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.beans.property.*;


/**
 * create a web browsing gui
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Date:</b> 2020/04/21 <i>(due date)</i>
 * @version <b>Project:</b> USU-Sp2020: CS-2410 GUI's -> Final Project: basicFX-webBrowser -> BasicFX_webBrowser main application Class
 */
public class BasicFX_webBrowser extends Application {

    public static void main(String[] args) {
        try{
            launch(args);
        } catch (Exception ex) { System.out.printf("\n%s", ex.getMessage()); }
    }


    @Override
    public void start(Stage mainStage) throws Exception {
        // - Name the Scene ----
        mainStage.setTitle("BasicFX-webBrowser - a02201315");

        // - Create the Scene Graph ----
        var root = new StackPane();
        root.getChildren().add(new Label("Hello World - From JavaFX"));

        // - Finish Creating Interface ----
        mainStage.setScene(new Scene(root));

        // - Additional Action ----


        // - Display the GUI ----
        mainStage.show();
    }
}