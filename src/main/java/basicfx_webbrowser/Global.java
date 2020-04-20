package basicfx_webbrowser;

import basicfx_webbrowser.browser.BookmarkManager;
import basicfx_webbrowser.browser.HistoryManager;
import basicfx_webbrowser.browser.SessionManager;
import basicfx_webbrowser.browser.SettingsManager;

public final class Global {

    public static final SettingsManager settings = new SettingsManager();
    public static final BookmarkManager bookmarks = new BookmarkManager();
    public static final HistoryManager history = new HistoryManager();
    public static final SessionManager session = new SessionManager();
    public static final String appDataDir = "AppData/";

}