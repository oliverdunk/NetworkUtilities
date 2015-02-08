package me.olivervscreeper.networkutilities.utils;

import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.serialization.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Implementation of the Hastebin API
 */
public class PasteUtils {

  /**
   * A simple implementation of the Hastebin Client API, allowing data to be pasted online for
   * players to access.
   *
   * @param urlParameters The string to be sent in the body of the POST request
   * @return A formatted URL which links to the pasted file
   */
  public synchronized static String paste(String urlParameters) {
    if(NetworkUtilities.plugin.getConfig().getBoolean("hasteEnabled") == false){
      NetworkUtilities.logger.log("PasteUtils", "Attempted to paste a String, but this feature is disabled.");
      return null;
    }
    HttpURLConnection connection = null;
    try {
      //Create connection
      URL url = new URL("http://www.hastebin.com/documents");
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoInput(true);
      connection.setDoOutput(true);

      //Send request
      DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.flush();
      wr.close();

      //Get Response
      BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      return "http://www.hastebin.com/" + new JSONObject(rd.readLine()).getString("key");
    } catch (Exception ex) {
      return null;
    } finally {
      if (connection == null) {
        return null;
      }
      connection.disconnect();
    }
  }

}
