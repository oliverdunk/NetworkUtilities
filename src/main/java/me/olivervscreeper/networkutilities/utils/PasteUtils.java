package me.olivervscreeper.networkutilities.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Modified implementation of https://code.google.com/p/pastebin-click/
 */
public class PasteUtils {

    private static String pasteURL = "http://www.pastebin.com/api/api_post.php";

    public static String makePaste(String type, String key, String title, String text) {
        try {
            String content = URLEncoder.encode(text, "UTF-8");
            String titler = URLEncoder.encode(title, "UTF-8");
            String data = "api_option=paste&api_user_key=" + key
                    + "&api_paste_private=0&api_paste_name=" + titler
                    + "&api_paste_expire_date=N&api_paste_format=" + type
                    + "&api_dev_key=" + key + "&api_paste_code=" + content;
            String response = page(pasteURL, data);
            return response;
        }catch(Exception ex){
            return null;
        }
    }

    private static String page(String uri, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
