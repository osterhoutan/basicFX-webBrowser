package basicfx_webbrowser.browser;

import java.security.KeyStore.Entry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Manager for web browsing history in a Web Browser.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 Event Driven Programing and GUIs
 *          -> Final Project: basicFX_webBrowser
 */
public final class HistoryManager extends JsonManager<JSONArray> {

    // - Class Attributes -------
    // private ArrayList<HistoryEntry> histroyArray;


    // - Class Constructors -------
    public HistoryManager() {
        // historyArray = (ArrayList<HistoryEntry>) json; 
    }


    // - Public Methods --------
    public void addHistory(String url) {
        json.add(0, new HistoryEntry(url));
        try { this.write(); } catch (Exception ex) {System.err.println("\n\n\tERROR: Failed to save history.\n");}
    }


    public JSONArray getHistory() {
        return json;
    }

    public boolean remove(HistoryEntry entry) {        
        return json.remove(entry);
    }

    public void clear() {
        json.clear();
    }


}