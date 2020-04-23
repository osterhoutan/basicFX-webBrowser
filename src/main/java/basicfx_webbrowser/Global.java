package basicfx_webbrowser;

import basicfx_webbrowser.browser.BookmarkManager;
import basicfx_webbrowser.browser.HistoryManager;
import basicfx_webbrowser.browser.SessionManager;
import basicfx_webbrowser.browser.SettingsManager;
import basicfx_webbrowser.control.BrowserController;
import basicfx_webbrowser.control.MainController;
import basicfx_webbrowser.control.MenuController;
import basicfx_webbrowser.control.menu.BookmarkController;
import basicfx_webbrowser.control.menu.HistoryController;
import basicfx_webbrowser.control.menu.SettingsController;

public final class Global {

    public static final String name = "basicFx-webBrowser";
    public static final String version = "1.0";
    public static final String webKitBase = "AppleWebKit/555.99 JavaFX 8.0";
    public static final String appDataDir = "AppData/";
    public static final String urlRegEx = "^(http[s]?:\\/\\/)?[^\\s([\"<,>]*\\.\\[^\\[\",><]*$";
    public static final String absFilePathRegEx = "^(?:[fF]ile\\:(?:[\\/\\\\](?:(?:\\d{1,3}\\.){3}\\d{1,3}(?:\\:\\d{1,6})?|(?:[a-zA-Z0-9_]{3,}))?[\\/\\\\]|[\\/\\\\]{2}))?(?:[a-zA-Z]:|~|%[A-Z_]{3,}%|\\$\\{?.+\\}?)?[\\/\\\\](?:[^\\{\\}\\[\\]\\n\\t\\&\\%\\$\\#\\@\\*\\=\\^\\~\\`\\;\\,\\<\\>\\!\\:\\/\\\\]{3,}[/\\\\])*[^\\{\\}\\[\\]\\n\\t\\&\\%\\$\\#\\@\\*\\=\\^\\~\\`\\;\\,\\<\\>\\!\\:\\/\\\\]{3,}[^/\\\\]$";

    public static final String webAgentName = String.format("%s %s - %s", name, version, webKitBase);

    // - persistent asset managers ----------
    public static final SettingsManager settings = new SettingsManager();
    public static final BookmarkManager bookmarks = new BookmarkManager();
    public static final HistoryManager history = new HistoryManager();
    public static final SessionManager session = new SessionManager();    

    // - GUI element Global access Points --------
    public static MainController appGUI;
    public static MenuController menuGUI;
    public static BookmarkController bookmarkGUI;
    public static BrowserController browserGUI;
    public static HistoryController historyGUI;
    public static SettingsController settingsGUI;

}