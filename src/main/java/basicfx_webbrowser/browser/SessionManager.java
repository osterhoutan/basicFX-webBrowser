package basicfx_webbrowser.browser;

import java.util.ArrayList;

import com.google.gson.Gson;

import org.json.simple.JSONArray;

/**
 * Store and save data about the current browsing session.
 * 
 * @author Andrew Osterhout (a02201315)
 * @version <b>Project:</b> USU-Sp2020: CS-2410 GUI's -> Final Project:
 *          basicFX-webBrowser -> BasicFX_webBrowser main application Class
 */
public final class SessionManager extends JsonManager<JSONArray> {
    
    // - Class Attributes -------
    private Gson gson = new Gson();


    // - Class Constructors -------
    public SessionManager() {
    }


    // - Public Settings value Getter --------

    public boolean remove(int id) {
        return json.remove(id)!=null;
    }


    public boolean addTab(SessionEntry entry) {
        return json.add(gson.toJson(entry));
    }


    // public boolean insertTabAt(int id, SessionEntry entry) {
    //     // TODO: make this work if rearranging tab feature gets implemented
    //     int size = json.size();
    //     if (id > size) return false;
    //     if (id == size) return json.add(gson.toJson(entry));
    //     if (json.get(id) != null)
    //         if (json.add(json.remove(id)))
    //             return json.add(entry);
    //     return false;
    // }


    public ArrayList<SessionEntry> asList() {
        ArrayList<SessionEntry> temp = new ArrayList<>();
        json.subList(0, json.size()-1).forEach((entry) -> {
            temp.add(gson.fromJson((String) entry, SessionEntry.class));
        });
        return temp;
    }


    public ArrayList<SessionEntry> asList(int startIndex, int endIndex) {
        ArrayList<SessionEntry> temp = new ArrayList<>();
        json.subList(startIndex, endIndex).forEach((entry) -> {
            temp.add(gson.fromJson((String) entry, SessionEntry.class));
        });
        return temp;
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