package basicfx_webbrowser.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

/**
 * Abstract parent of all of the FXML Controllers in the basicFX_webBrowser
 * project.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs
 *          -> Final Project: basicFX_webBrowser
 */
public abstract class FXML_Controller implements Initializable {

    public abstract void initialize(URL url, ResourceBundle resourceBundle);

}