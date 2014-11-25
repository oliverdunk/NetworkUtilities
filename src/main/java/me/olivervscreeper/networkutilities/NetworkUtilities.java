package me.olivervscreeper.networkutilities;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;

/**
 * Created on 23/11/2014.
 *
 * @author OliverVsCreeper
 */
public class NetworkUtilities extends JavaPlugin {

    public static String version = "1.0-SNAPSHOT";
    public static String compatibility = "Bukkit 1.7.10-R0.1-SNAPSHOT";

    /**
     * Default bukkit onEnable() method. Triggers the plugin launch one
     * the Bukkit wrapper has loaded and is ready to begin the handling
     * of plugins.
     */
    public void onEnable(){
        bootPluginMetrics(); //Attempts to boot metrics system.
        log("Version " + version + " now running.");
        log("This version is compatible with " + compatibility);
        //TODO: Initialize any null classes required at runtime
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
        } catch (IOException e) {
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
    private void log(String message){
        getServer().getLogger().info("NU - " + message);
    }

}
