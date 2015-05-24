package me.olivervscreeper.networkutilities.utils;

import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.messages.Message;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommunicationUtils implements Listener{

    public static String getStringFromURL(String URLString){
        try {
            URL URL = new URL(URLString);
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return reader.readLine();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNotice(){
        String result = getStringFromURL("http://www.olivervscreeper.co.uk/api/NetworkUtilities.html");
        JSONObject object = new JSONObject(result);
        if(!object.has("message")) return "";
        if (object.has("version")) {
            if (object.getInt("version") > NetworkUtilities.versionID) return object.getString("message");
            return "";
        } else {
            return object.getString("message");
        }
    }

    public static boolean isOutdated(){
        String result = getStringFromURL("http://www.olivervscreeper.co.uk/api/NetworkUtilities.html");
        JSONObject object = new JSONObject(result);
        if (object.has("current")) {
            if (object.getInt("current") > NetworkUtilities.versionID) return true;
            return false;
        } else {
            return true;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(!event.getPlayer().isOp()) return;
        if(isOutdated()) new Message(Message.NETWORK).addRecipient(event.getPlayer()).send(ChatColor.RED + "Your current version is outdated.");
        if(getNotice().equals("")) return;
        new Message(Message.NETWORK).addRecipient(event.getPlayer()).send(ChatColor.translateAlternateColorCodes('&', getNotice()));
    }

}
