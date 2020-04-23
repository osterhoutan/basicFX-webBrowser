package basicfx_webbrowser.browser;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


abstract class JsonManager<O extends Object> {

    // - Class Attributes -----------
    protected File file;
    O json;


    // - Class Constructors ----------
    



    // - Class Public Methods ---------
    public void setFile(String filePath) throws Exception {
        file = new File(filePath);
        if (!file.exists()) file.createNewFile();
    }

    public void read() throws Exception {
        Object temp = new JSONParser().parse(new FileReader(file));
        json = (O) temp;
    }

    public void write() throws Exception {
        PrintWriter pw = new PrintWriter(file);
        if (json instanceof JSONObject) {
            JSONObject obj = (JSONObject) json;
            pw.write(obj.toJSONString());

        } else if (json instanceof JSONArray) {
            JSONArray obj = (JSONArray) json;
            pw.write(obj.toJSONString());
        }
        pw.flush(); 
        pw.close();
    }

    // public <T> T getValue(String key) {
        
    // }



    // - Class Private Methods --------




    // - Private internal Class -------
    
    
}