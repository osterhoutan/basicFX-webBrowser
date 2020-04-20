package basicfx_webbrowser.browser;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.web.WebEngine;

/**
 * Store and save data about the current browsing session.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 GUI's -> Final Project:
 *          basicFX-webBrowser -> BasicFX_webBrowser main application Class
 */
public final class SessionManager extends JsonManager<JSONArray> {
    
    // - Class Attributes -------
    private JSONArray session;


    // - Class Constructors -------
    public SessionManager() {
        this.session = this.json;
    }


    // - Public Settings value Getter --------

    public boolean remove(int id) {
        return json.remove(id)!=null;
    }


    public boolean addTab(SessionEntry entry) {
        return json.add(entry);
    }


    public boolean addTab(int id, SessionEntry entry) {
        if (json.get(id) != null)
            if (json.add(json.remove(id)))
                return json.add(entry);
        return false;
    }


    public ArrayList<SessionEntry> asList() {
        return (ArrayList<SessionEntry>) json.subList(0, json.size()-1);
    }


    public ArrayList<SessionEntry> asList(int startIndex, int endIndex) {
        return (ArrayList<SessionEntry>) json.subList(0, json.size()-1);
    }




    // public int add


    // public List<SessionEntry> getSessionList() {
    //     ArrayList<SessionEntry> session = new ArrayList<>();
    //     // json.forEach((String Key, JSONObject entry) -> {

    //     // });
    //     return session;
    // }






    // - Public Settings value Setters ----------




    

    // - Class Public Methods ------------





    // - Class Private Methods -----------





    // - Private attribute Getters and Setters --------

}