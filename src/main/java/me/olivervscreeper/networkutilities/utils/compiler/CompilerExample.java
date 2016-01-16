package me.olivervscreeper.networkutilities.utils.compiler;

import org.bukkit.Bukkit;

/**
 * @author OliverVsCreeper
 */
public class CompilerExample extends CompilerClass {

    @Override
    public void run() {
        Bukkit.getLogger().info("It works! Victory!");
        Bukkit.getLogger().info("Shall we celebrate with some tea and cake?");
    }

}
