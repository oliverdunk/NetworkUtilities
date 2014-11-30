package me.olivervscreeper.networkutilities.game.states;

import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public abstract class GameState implements Listener{

    private Game gameInstance;

    public GameState(Game game){
        this.gameInstance = game;
    }

    int runtime = 0;

    public int getRuntime() {return runtime;}
    public void incrementRuntime(){runtime++;}

    public abstract String getName();
    public abstract String getDisplayName();

    public abstract Boolean onStateBegin();
    public abstract Boolean onStateEnd();

    public abstract void tick();

    public void registerListener(Listener classInstance){
        Bukkit.getPluginManager().registerEvents(classInstance, NetworkUtilities.plugin);
    }

    public void unregisterListener(Listener classInstance){
        HandlerList.unregisterAll(classInstance);
    }

}
