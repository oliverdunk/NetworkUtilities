package me.olivervscreeper.networkutilities;

import me.olivervscreeper.networkutilities.utils.PasteUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author OliverVsCreeper
 */
public class NULogger {

  public String log = "";

  /**
   * Constructor creates a new logger instance.
   *
   * @param versionData If set to true, this will cause the logfile to be prefixed with several
   *                    lines based on version information for debug purposes
   */
  public NULogger(boolean versionData) {
    if (!versionData) return;
    log("NU", "NetworkUtilities generated log file");
    log("NU", "Server running version " + NetworkUtilities.version);
    log("NU", "Confirmed compatible with " + NetworkUtilities.compatibility + "\n");
  }

  /**
   * Suffixes a new line to the stored log string, with a new line separator.
   *
   * @param from The display name of the class or system generating the logger message
   * @param logMessage The message which should be logged
   */
  public void log(String from, String logMessage) {
    if (log != "") log += "\n";
    log += getTimePrefix() + " [" + from + "] " + logMessage;
  }

  /**
   * Uses SimpleDateFormat to create a timestamp in the Bukkit Logger
   * format
   *
   * @return Date in the format "[HH:mm:ss]"
   */
  public String getTimePrefix() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss]");
    return sdf.format(cal.getTime());
  }

  /**
   * Uses the PasteUtils class to get a Url to a Hastebin page containing the log. Generally, this
   * is formatted by Hastebin in a log file format.
   *
   * @return String Url of the Hastebin upload
   */
  public String getLog() {
    return PasteUtils.paste(log);
  }

}