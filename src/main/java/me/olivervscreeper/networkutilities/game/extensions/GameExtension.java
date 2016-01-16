package me.olivervscreeper.networkutilities.game.extensions;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import lombok.Getter;
import lombok.Setter;
import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.game.Game;

/**
 * @author OliverVsCreeper
 */
public abstract class GameExtension {

    public Game gameInstance;
    @Getter @Setter private String name;

    public GameExtension(Game gameInstance, String name) {
        this.gameInstance = gameInstance;
        this.name = name;
    }

    public abstract boolean onEnable();

    public void registerListener(Listener classInstance) {
        Bukkit.getPluginManager().registerEvents(classInstance, NetworkUtilities.plugin);
    }

    public void unregisterListener(Listener classInstance) {
        HandlerList.unregisterAll(classInstance);
    }

}
