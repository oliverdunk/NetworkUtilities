package me.olivervscreeper.networkutilities.messages;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void sendTitle(Player player, String title, int in, int stay, int out){
        PlayerConnection titleConnection = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutTitle
                titlePacketPlayOutTimes =
                new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, in, stay, out);
        titleConnection.sendPacket(titlePacketPlayOutTimes);

        IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle
                packetPlayOutTitle =
                new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
        titleConnection.sendPacket(packetPlayOutTitle);
    }

    public static void sendSubtitle(Player player, String title, int in, int stay, int out){
        PlayerConnection subtitleConnection = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutTitle
                subtitlePacketPlayOutTimes =
                new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, in, stay, out);
        subtitleConnection.sendPacket(subtitlePacketPlayOutTimes);

        IChatBaseComponent subtitleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle
                subtitlePacketPlayOutSubTitle =
                new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleSub);
        subtitleConnection.sendPacket(subtitlePacketPlayOutSubTitle);
    }

    public static void sendActionbar(Player player, String message){
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }

}
