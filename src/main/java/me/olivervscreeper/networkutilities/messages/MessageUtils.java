package me.olivervscreeper.networkutilities.messages;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void sendTitle(Player player, String title, int in, int stay, int out){
        PlayerConnection titleConnection = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutTitle
                titlePacketPlayOutTimes =
                new PacketPlayOutTitle(EnumTitleAction.TIMES, null, in, stay, out);
        titleConnection.sendPacket(titlePacketPlayOutTimes);

        IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle
                packetPlayOutTitle =
                new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
        titleConnection.sendPacket(packetPlayOutTitle);
    }

    public static void sendSubtitle(Player player, String title, int in, int stay, int out){
        PlayerConnection subtitleConnection = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutTitle
                subtitlePacketPlayOutTimes =
                new PacketPlayOutTitle(EnumTitleAction.TIMES, null, in, stay, out);
        subtitleConnection.sendPacket(subtitlePacketPlayOutTimes);

        IChatBaseComponent subtitleSub = ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle
                subtitlePacketPlayOutSubTitle =
                new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleSub);
        subtitleConnection.sendPacket(subtitlePacketPlayOutSubTitle);
    }

    public static void sendActionbar(Player player, String message){
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }

}
