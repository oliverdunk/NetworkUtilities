package me.olivervscreeper.networkutilities.utils;

import me.olivervscreeper.networkutilities.messages.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CommunicationUtils implements Listener{

    public static String getNotice(){
        String URLString = "http://olivervscreeper.co.uk/api/NetworkUtilities.html";
        try {
            URL URL = new URL(URLString);
            URLConnection connection = URL.openConnection();
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
        new Message(Message.NETWORK).addRecipient(event.getPlayer()).send(getNotice());
    }

}
