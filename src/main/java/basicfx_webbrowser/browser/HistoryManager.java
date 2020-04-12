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



    // - Class Constructors -------
    public HistoryManager() {
        
    }


    // - Public Methods --------
    public void addHistory(String url) {
        JSONObject entry = new JSONObject();
        entry.put("time", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        entry.put("url", url);
    }


    public ArrayList<HistoryEntry> getHistory() {
        ArrayList<HistoryEntry> history = new ArrayList<>();
        json.stream().forEach(i -> {
            JSONObject item = (JSONObject) i;
            HistoryEntry entry = new HistoryEntry((String) item.get("url"), (String) item.get("time"));
            history.add(entry);
        });
        return history;
    }

    public boolean remove(HistoryEntry entry) {    
        JSONObject item = new JSONObject();
        item.put("url", entry.url);    
        item.put("time", entry.time);    
        return json.contains(item);
    }

    public void clear() {
        json.clear();
    }


}