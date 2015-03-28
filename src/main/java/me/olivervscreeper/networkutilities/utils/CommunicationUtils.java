package me.olivervscreeper.networkutilities.utils;

import me.olivervscreeper.networkutilities.messages.Message;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommunicationUtils implements Listener{

    public static String getNotice(){
        String URLString = "http://www.olivervscreeper.co.uk/api/NetworkUtilities.html";
        try {
            URL URL = new URL(URLString);
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(!event.getPlayer().isOp()) return;
        if(getNotice().equals("")) return;
        new Message(Message.NETWORK).addRecipient(event.getPlayer()).send(ChatColor.translateAlternateColorCodes('&', getNotice()));
    }

}
