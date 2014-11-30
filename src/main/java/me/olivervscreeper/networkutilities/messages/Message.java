package me.olivervscreeper.networkutilities.messages;

import me.olivervscreeper.networkutilities.game.players.GamePlayer;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class Message {

    private String PREFIX;
    private List<UUID> recipients = new ArrayList<UUID>();

    private int titleIn = 2;
    private int titleOut = 2;
    private int titleStay = 5;

    public static final String NETWORK = "&8[&bNetworkUtilities&8] &7%m";
    public static final String INFO = "&8[&2Info&8] &7%m";
    public static final String BLANK = "%m";

    public Message(String PREFIX){
        this.PREFIX = PREFIX;
    }

    public Message addRecipient(Player player){
        recipients.add(player.getUniqueId());
        return this;
    }

    public Message addRecipient(GamePlayer player){
        recipients.add(player.getUniqueIdentifier());
        return this;
    }

    public Message addRecipient(String player){
        recipients.add(Bukkit.getPlayerExact(player).getUniqueId());
        return this;
    }

    public Message addRecipient(List<Player> players){
        for(Player player : players){
            recipients.add(player.getUniqueId());
        }
        return this;
    }

    public Message setTimings(int in, int stay, int out){
        titleIn = in;
        titleStay = stay;
        titleOut = out;
        return this;
    }

    public void send(String message){
        message = ChatColor.translateAlternateColorCodes('&', PREFIX.replace("%m", message));
        for(UUID uniqueUserIdentifier : recipients){
            Player player = Bukkit.getPlayer(uniqueUserIdentifier);
            player.sendMessage(message.replace("%p", player.getName()));
        }
        recipients.clear();
    }

    public void send(String message, MessageDisplay display){
        message = ChatColor.translateAlternateColorCodes('&', PREFIX.replace("%m", message));
        for(UUID uniqueUserIdentifier : recipients){
            Player player = Bukkit.getPlayer(uniqueUserIdentifier);
            String newMessage = message.replace("%p", player.getName());
            switch(display) {
                case CHAT:
                    player.sendMessage(newMessage);
                    break;
                case ACTIONBAR:
                    IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + newMessage + "\"}");
                    PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
                    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
                    break;
                case TITLE:
                    //TODO: Implement when Spigot releases API
                    new Message(Message.INFO).addRecipient(player).send("Not Yet Implemented!");
                    break;
                case SUBTITLE:
                    //TODO: Implement when Spigot releases API
                    new Message(Message.INFO).addRecipient(player).send("Not Yet Implemented!");
                    break;
            }
        }
        recipients.clear();
    }

}
