package basicfx_webbrowser.browser;

import java.util.ArrayList;

import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;

public class SessionEntry {
    public int currentIndex;
    public ArrayList<Entry> history = new ArrayList<>();

    public SessionEntry() {}

    public SessionEntry(WebHistory history) {
        this.history.addAll(history.getEntries());
        this.currentIndex = history.getCurrentIndex();
    }
}