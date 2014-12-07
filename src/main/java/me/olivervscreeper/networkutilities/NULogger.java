package me.olivervscreeper.networkutilities;

import me.olivervscreeper.networkutilities.utils.PasteUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by User on 07/12/2014.
 */
public class NULogger {

    public String log = "";

    public NULogger(Boolean versionData){
        if(!versionData) return;
        log("NetworkUtilities generated log file");
        log("Server running version " + NetworkUtilities.version);
        log("Confirmed compatible with " + NetworkUtilities.compatibility);
        log("");
    }

    public void log(String toLog){
        if(!(log == "")) log = log + "\n";
        log = log + getTimePrefix() + " " + toLog;
    }

    private String getTimePrefix(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss]");
        return sdf.format(cal.getTime());
    }

    public String getLog(){
        return PasteUtils.paste(log);
    }

}
