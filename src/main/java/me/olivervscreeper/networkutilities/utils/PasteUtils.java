package me.olivervscreeper.networkutilities.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Implementation of the Hastebin API
 *
 * @author OliverVsCreeper
 */
public class PasteUtils {

    private static String pasteURL = "http://hastebin.com/";

    /**
     * A simple implementation of the Hastebin Client API, allowing data to be pasted online for
     * players to access.
     *
     * @param urlParameters The string to be sent in the body of the POST request
     * @return A formatted URL which links to the pasted file
     */
    public synchronized static String paste(String urlParameters) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(pasteURL + "documents");
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
            return pasteURL + new JSONObject(rd.readLine()).getString("key");

        } catch (IOException e) {
            return null;
        } finally {
            if (connection == null) return null;
            connection.disconnect();
        }
    }

    /**
     * Returns the URL of the server being used.
     *
     * @return API to use for posting data
     */
    public static String getPasteURL() {
        return pasteURL;
    }

    /**
     * Sets the URL used by the paste method, allowing for the server logs are pasted to to be
     * dynamically changed.
     *
     * @param URL API URL of HasteBin instance
     */
    public static void setPasteURL(String URL) {
        pasteURL = URL;
    }

    /**
     * Grabs a HasteBin file from the internet and attempts to return the file with formatting
     * intact.
     *
     * @return String HasteBin Raw Text
     */
    public static synchronized String getPaste(String ID) {
        String URLString = pasteURL + "raw/" + ID + "/";
        try {
            URL URL = new URL(URLString);
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String paste = "";
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.contains("package")) continue;
                if (paste.equals("")) paste = line;
                else paste = paste + "\n" + line;
            }
            return paste;
        } catch (IOException e) {
            return "";
        }
    }


}
