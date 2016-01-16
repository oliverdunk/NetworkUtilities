package me.olivervscreeper.networkutilities.game.states;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.game.Game;

/**
 * @author OliverVsCreeper
 */
@Getter
public abstract class GameState implements Listener {

    @Getter(AccessLevel.NONE) public Game gameInstance;
    @Setter private String name;
    @Setter private String displayName;
    private int runtime = 0;

    /**
     * Default constructor for a GameState
     *
     * @param game        Instance of the game which is handling the state
     * @param name        Raw name of the state for display in log files
     * @param displayName Formatted display name for use in chat messages
     */
    public GameState(Game game, String name, String displayName) {
        this.gameInstance = game;
        this.name = name;
        this.displayName = displayName;
    }

    public void incrementRuntime() {
        runtime++;
    }

    //If either return false, a GameState change can not take place
    public abstract boolean onStateBegin();

    public abstract boolean onStateEnd();

    public abstract void tick();

    public void registerListener(Listener classInstance) {
        Bukkit.getPluginManager().registerEvents(classInstance, NetworkUtilities.plugin);
    }

    public void unregisterListener(Listener classInstance) {
        HandlerList.unregisterAll(classInstance);
    }

}