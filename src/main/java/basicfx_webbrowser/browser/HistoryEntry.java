package basicfx_webbrowser.browser;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

public class HistoryEntry extends JSONObject {
    private static final long serialVersionUID = 656_345_213l;

    public HistoryEntry(String url, String time) {
        this.put("url", url);
        this.put("time", time);
    }

    public HistoryEntry(String url) {
        this.put("url", url);
        this.put("time", (new SimpleDateFormat("yyyy/MM/dd E HH:mm:ss.SS (z)")).format(new Date()));
    }

    public String toString() {
        return String.format("%s\n    %s", this.get("time"), this.get("url"));
    }

    public String toJSONString() {
        return String.format("{\"time\":\"%s\",\"url\":\"%s\"}",
                                this.get("time"), this.get("url"));
    }

    public String getUrl() { return (String) this.get("url"); }
    public String getTime() { return (String) this.get("time"); }
}