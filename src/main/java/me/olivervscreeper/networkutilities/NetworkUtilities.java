package me.olivervscreeper.networkutilities;

import me.olivervscreeper.networkutilities.command.Command;
import me.olivervscreeper.networkutilities.command.CommandManager;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.messages.MessageDisplay;

import me.olivervscreeper.networkutilities.utils.compiler.CompilerUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import javax.tools.ToolProvider;
import java.io.IOException;
import java.util.List;

/**
 * Created on 23/11/2014.
 *
 * @author OliverVsCreeper
 */
public class NetworkUtilities extends JavaPlugin {

  public static String version = "1.4.4-RELEASE";
  public static String compatibility = "Spigot 1.8.8-R0.1-SNAPSHOT";

  public static Plugin plugin;
  public static CommandManager manager;
  public static NULogger logger = new NULogger(true);

  /**
   * Default Bukkit onEnable() method. Triggers the plugin launch one the Bukkit wrapper has loaded
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
    logger.log("NU", "Registered events into Bukkit");
    mcStats();
    logger.log("NU", "Enabled MCStats connection");

    logger.log("NU", "Plugin initialisation complete.");

  }

  private void mcStats() {
    try {
      Metrics metrics = new Metrics(this);
      metrics.start();
    } catch (IOException e) {
      logger.log("NU", "Failed to enable MCStats connection");
    }
  }

  /**
   * Logs a message to the console using the default Minecraft logger. A prefix of
   * "[NetworkUtilities] - " is prefixed, and the message is sent. Intended only for use by the
   * NetworkUtilities plugin.
   *
   * @param message message for the console to log
   */
  public static void log(String message) {
    if(plugin != null) plugin.getServer().getLogger().info("[NetworkUtilities] - " + message);
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
    new Message(Message.BLANK).setTimings(2, 15, 60).addRecipient(player)
        .send("Using NetworkUtilities", MessageDisplay.TITLE);
    new Message(Message.BLANK).setTimings(2, 15, 60).addRecipient(player)
        .send("Version: " + version + ".", MessageDisplay.SUBTITLE);
    new Message(Message.BLANK).addRecipient(player)
        .send("Intended for " + compatibility, MessageDisplay.ACTIONBAR);
  }

  @Command(label = "nu-log", permission = "nu.all")
  public void nuLogCommand(final Player player, List<String> args) {
      new Message(Message.INFO).addRecipient(player).send("Uploading log...");
      getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
          public void run() {
              new Message(Message.INFO).addRecipient(player).send("Log File: " + logger.getLog());
          }
      });
  }

  @Command(label = "nu-run", permission = "nu.all")
  public void onRunCommand(final Player player, final List<String> args){
    if(args.size() == 1){
      new Message(Message.WARNING).addRecipient(player).send("This is a powerful command! Use with caution.");
      new Message(Message.NETWORK).addRecipient(player).send("Attempting to run Haste ID: " + args.get(0));
      getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
          public void run() {
              if(CompilerUtils.runString(args.get(0))){
                  new Message(Message.NETWORK).addRecipient(player).send("Successfully executed!");
              }else{
                  new Message(Message.WARNING).addRecipient(player).send((ToolProvider.getSystemJavaCompiler() == null)
                          ? "Server is not running in a JDK!" : "An error occurred while executing!");
              }
          }
      });
      return;
    }
    new Message(Message.WARNING).addRecipient(player).send("Usage: /run <HasteID>");
  }



}