package basicfx_webbrowser.browser;

import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    private ArrayList<SessionEntry> session = new ArrayList<>();
    private static final Type entryListType = new TypeToken<ArrayList<SessionEntry>>(){}.getType();


    // - Class Constructors -------
    public SessionManager() {
    }

    @Override
    public void read() throws Exception {
        session = gson.fromJson(new FileReader(file), entryListType);
        if (session==null) session = new ArrayList<SessionEntry>();
        // System.err.println("The session File Location is: "+file.getAbsolutePath()+"\n");
        // System.err.println();
    }

    @Override
    public void write() throws Exception {
        PrintWriter pw = new PrintWriter(file);
        pw.write(gson.toJson(session)); 
        pw.flush(); 
        pw.close();
    }


    // - Public Settings value Getter --------

    public boolean remove(int id) {
        Object obj = session.remove(id);
        try { write(); } catch (Exception ex) {System.err.println("\n\n\tERROR: failed to write session to file after removing a Session Entry! ("+ex.getMessage()+")\n");}
        return obj!=null;
    }


    public int addTab(SessionEntry entry) {
        session.add(entry);
        try { write(); } catch (Exception ex) {System.err.println("\n\n\tERROR: failed to write session to file after adding a Session Entry! ("+ex.getMessage()+")\n");}
        return session.size()-1;
    }

    public void clearSession() {
        session.clear();
    }

    public int getSessionSize() {
        return session.size();
    }


    // public boolean insertTabAt(int id, SessionEntry entry) {
    //     // TODO: make this work if rearranging tab feature gets implemented
    //     int size = session.size();
    //     if (id > size) return false;
    //     if (id == size) return session.add(gson.toJson(entry));
    //     if (session.get(id) != null)
    //         if (session.add(session.remove(id)))
    //             return session.add(entry);
    //     return false;
    // }


    public ArrayList<SessionEntry> asList() {
        return session;
    }


    public List<SessionEntry> asList(int startIndex, int endIndex) {
        return session.subList(startIndex, endIndex);
    }


    public void write(boolean clear) throws Exception {
        if (clear) clearSession();
        write();
    }



    // public int add


    // public List<SessionEntry> getSessionList() {
    //     ArrayList<SessionEntry> session = new ArrayList<>();
    //     // session.forEach((String Key, JSONObject entry) -> {

    //     // });
    //     return session;
    // }






    // - Public Settings value Setters ----------




    

    // - Class Public Methods ------------





    // - Class Private Methods -----------





    // - Private attribute Getters and Setters --------

}