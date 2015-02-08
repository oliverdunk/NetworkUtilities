package me.olivervscreeper.networkutilities;

import me.olivervscreeper.networkutilities.command.Command;
import me.olivervscreeper.networkutilities.command.CommandManager;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.messages.MessageDisplay;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Created on 23/11/2014.
 *
 * @author OliverVsCreeper
 */
public class NetworkUtilities extends JavaPlugin {

  public static String version = "1.3-RELEASE";
  public static String compatibility = "Spigot 1.8-R0.1-SNAPSHOT";

  public static Plugin plugin;
  public static CommandManager manager;
  public static NULogger logger;

  /**
   * Default bukkit onEnable() method. Triggers the plugin launch one the Bukkit wrapper has loaded
   * and is ready to begin the handling of plugins.
   */
  public void onEnable() {
    plugin = this;
    logger = new NULogger(true);
    log("Version " + version + " now running.");
    log("Working with " + compatibility);
    manager = new CommandManager(this);
    logger.log("NU", "New command manager created");
    manager.registerCommands(this);
    logger.log("NU", "Default commands loaded into Bukkit");

    logger.log("NU", "Plugin initialisation complete.");
  }

  /**
   * Logs a message to the console using the default Minecraft logger. A prefix of
   * "[NetworkUtilities] - " is prefixed, and the message is sent. Intended only for use by the
   * NetworkUtilities plugin.
   *
   * @param message message for the console to log
   */
  public static void log(String message) {
    plugin.getServer().getLogger().info("[NetworkUtilities] - " + message);
  }

  /**
   * Simple method to get the default CommandManager which is created and used by the
   * NetworkUtilities commands
   *
   * @return The instance of CommandManager in use by NetworkUtilities
   */
  public static CommandManager getCommandManager() {
    return manager;
  }


  @Command(label = "nu", permission = "none", priority = 1)
  public void nuVersionCommand(Player player, List<String> args) {
    new Message(Message.BLANK).addRecipient(player)
        .send("Using NetworkUtilities", MessageDisplay.TITLE);
    new Message(Message.BLANK).addRecipient(player)
        .send("Version: " + version + ".", MessageDisplay.SUBTITLE);
    new Message(Message.BLANK).addRecipient(player)
        .send("Intended for " + compatibility, MessageDisplay.ACTIONBAR);
  }

  @Command(label = "nu-log", permission = "nu.all")
  public void nuLogCommand(Player player, List<String> args) {
    new Message(Message.INFO).addRecipient(player).send("Log File: " + logger.getLog());
  }

}
