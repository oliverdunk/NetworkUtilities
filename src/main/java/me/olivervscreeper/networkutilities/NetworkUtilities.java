package me.olivervscreeper.networkutilities;

import me.olivervscreeper.networkutilities.command.Command;
import me.olivervscreeper.networkutilities.command.CommandManager;
import me.olivervscreeper.networkutilities.permissions.OPPermissionSet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.util.List;

/**
 * Created on 23/11/2014.
 *
 * @author OliverVsCreeper
 */
public class NetworkUtilities extends JavaPlugin {

    public static String version = "1.0-SNAPSHOT";
    public static String compatibility = "Bukkit 1.7.10-R0.1-SNAPSHOT";

    public static Plugin plugin;
    public static CommandManager manager;

    /**
     * Default bukkit onEnable() method. Triggers the plugin launch one
     * the Bukkit wrapper has loaded and is ready to begin the handling
     * of plugins.
     */
    public void onEnable(){
        plugin = this;
        bootPluginMetrics(); //Attempts to boot metrics system
        log("Version " + version + " now running.");
        log("Version compatible with " + compatibility);
        manager = new CommandManager(this);
        manager.registerCommands(this.getClass());
        manager.setPermission("nu", new OPPermissionSet());
    }

    /**
     * Attempts to send basic statistics to the Metrics service.
     * The method will return after a short delay, based on
     * a connection the Metrics servers.
     *
     * @return      status of the stat submission
     * @see         "http://mcstats.org/"
     */
    public boolean bootPluginMetrics(){
        try {
            Metrics metrics = new Metrics(this);
            metrics.start(); //Sends metrics
            return true; //Success
        } catch (Exception e) {
            log("Failed to boot metrics system.");
            return false; //Send failure
        }
    }

    /**
     * Logs a message to the console using the default
     * Minecraft logger. A prefix of "NU - " is prefixed,
     * and the message is sent. Intended only for use
     * by the NetworkUtilities plugin.
     *
     * @param message message for the console to log
     */
    public static void log(String message){
        plugin.getServer().getLogger().info("NU - " + message);
    }

    @Command(command = "nu-version")
    public static void nuVersionCommand(Player player, List<String> args){
        player.sendMessage(ChatColor.WHITE + "This server is running NetworkUtilities version " + ChatColor.GRAY + version + ChatColor.WHITE + " for " + ChatColor.GRAY + compatibility + ".");
    }

    @Command(command = "nu")
    public static void nuCommand(Player player, List<String> args){
        player.sendMessage(ChatColor.WHITE + "Not Yet Implemented.");
    }

}
