package basicfx_webbrowser.browser;

import javafx.scene.web.WebEngine;

public class SessionEntry {
    public int id;
    public boolean pinned;
    public String url;
    public WebEngine engine;

    public SessionEntry(String url, WebEngine engine, boolean pinned) {
        this.engine = engine;
        this.url = url;
        this.pinned = pinned;
    }

    public SessionEntry(String url, WebEngine engine) {
        this.engine = engine;
        this.url = url;
        this.pinned = false;
    }

    public SessionEntry(WebEngine engine) {
        var temp = engine.getHistory().getEntries();
        this.engine = engine;
        this.url = temp.get(temp.size()-1).toString();
        this.pinned = false;
    }

    public SessionEntry(WebEngine engine, boolean pinned) {
        var temp = engine.getHistory().getEntries();
        this.engine = engine;
        this.url = temp.get(temp.size()-1).toString();
        this.pinned = pinned;
    }
}