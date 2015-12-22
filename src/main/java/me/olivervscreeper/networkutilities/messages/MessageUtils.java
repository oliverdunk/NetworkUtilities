package me.olivervscreeper.networkutilities.messages;

import org.bukkit.entity.Player;

/**
 * @author OliverVsCreeper
 */
public class MessageUtils {

    public static void sendTitle(Player player, String title, int in, int stay, int out) {
        new MessageReflector().send(0, player, title, in, stay, out);
    }

    public static void sendSubtitle(Player player, String title, int in, int stay, int out) {
        new MessageReflector().send(1, player, title, in, stay, out);
    }

    public static void sendActionbar(Player player, String title, int in, int stay, int out) {
        new MessageReflector().sendActionbar(player, title, in, stay, out);
    }

}
