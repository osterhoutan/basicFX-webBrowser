package basicfx_webbrowser.browser.settings;

import java.net.URLEncoder;

public enum SearchEngine {

    // - Enum Values ------------

    GOOGLE	("Google", "https://www.google.com/search?q=%s", "google:"),
        G_SCHOLAR	("Google Scholar", "https://scholar.google.com/scholar?hl=en&as_sdt=0%2C45&q=%s", "gscholar:"),
            // SCHOLAR	("Google Scholar", "https://scholar.google.com/scholar?hl=en&as_sdt=0%2C45&q=%s", "scholar:"),
        G_IMAGES	("Google Images", "https://www.google.com/search?tbm=isch&q=%s", "gimages:"),
            // IMAGES	("Google Images", "https://www.google.com/search?tbm=isch&q=%s", "images:"),
        YOUTUBE     ("Youtube", "https://www.youtube.com/results?q=%s", "youtube:"),
            // VIDEOS     ("Youtube", "https://www.youtube.com/results?q=%s", "video:"),
    BING	("Bing", "https://www.bing.com/search?q=%s&qs=ds&form=QBLH", "bing:"),
    DUCK	("Duck Duck GO", "https://duckduckgo.com/?q=%s&kp=-1&kl=us-en", "duck:"),
        // DDG	("Duck Duck GO", "https://duckduckgo.com/?q=%s&kp=-1&kl=us-en", "ddg:"),
    YAHOO	("Yahoo", "https://search.yahoo.com/search?p=%s", "yahoo:"),
    AMAZON  ("Amazon Store", "https://www.amazon.com/s?k=%s", "amazon:"),
    GITHUB  ("GitHub", "https://github.com/search?q=%s", "github:"),
    CUSTOM  ("Custom", "https://www.google.com/search?q=%s", "custom:");

    // - Enum Attributes ---------
    private String name;
    private String queryURL;
    private String searchHeader;


    // - Enum Constructors --------
    private SearchEngine(String name, String queryURL, String searchHeader) {
        this.name = name;
        this.queryURL = queryURL;
        this.searchHeader = searchHeader;
    }

    SearchEngine(String name, String queryURL) {
        this.name = name;
        this.queryURL = queryURL;
        this.searchHeader = null;
    }


    // - Custom Enum creation Methods --------

    public static SearchEngine setCustomEngine(String name, String queryURL, String searchHeader) {
        searchHeader = searchHeader.replace(":", "").toLowerCase().trim();
        if (!queryURL.contains("=%s") || searchHeader.length()<3 || !searchHeader.matches("[a-z]*")) 
            return null;
        CUSTOM.name = name.trim();
        CUSTOM.queryURL = queryURL.trim();
        CUSTOM.searchHeader = searchHeader+":";
        return CUSTOM;
    }

    public static SearchEngine setCustomEngine(String name, String queryURL) {
        if (!queryURL.contains("=%s")) return null;
        CUSTOM.name = name.trim();
        CUSTOM.queryURL = queryURL.toLowerCase().trim();
        CUSTOM.searchHeader = "custom:";
        return CUSTOM;
    }


    // - Enum Method Overrides -------
    @Override
    public String toString() {
        return this.name;
    }


    // - Enum Public Methods --------

    public static SearchEngine fromName(String name) {
        name = name.toLowerCase().trim();
        for (SearchEngine eng : SearchEngine.values())
            if (eng.name.toLowerCase().equals(name))
                return eng;
        return null;
    }


    public String getName() {
        return this.name;
    }

    public String getQueryURL() {
        return this.queryURL;
    }

    public String getSearchHeader() {
        return this.searchHeader;
    }


    public static boolean containsSearchHeader(String query) {
        query = query.trim();
        if (whichSearchEngine(query)==null) return false;
        return true;
    }


    public String compileQueryURL(String query) throws Exception {
        query = query.trim();
        SearchEngine eng = whichSearchEngine(query);
        if (eng==null)
            eng = this;
        else
            query = query.substring(eng.searchHeader.length()).trim();

        return String.format(eng.queryURL, URLEncoder.encode( query, "UTF-8"));
    }


    // - Enum Private Methods --------
    private static SearchEngine whichSearchEngine(String query) {
        query = query.toLowerCase();
        for (SearchEngine eng: SearchEngine.values())
            // if (eng.searchHeader!=null && query.substring(0, eng.searchHeader.length()-1).equals(eng.searchHeader))
            if (eng.searchHeader!=null && query.matches("^"+eng.searchHeader+".*"))
                return eng;
        return null;
    }
    
}