package me.olivervscreeper.networkutilities.game.events;

import me.olivervscreeper.networkutilities.game.NetworkArena;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created on 28/11/2014.
 *
 * @author OliverVsCreeper
 */
public class ArenaResetEvent extends Event{

    private static HandlerList handlers = new HandlerList();

    private NetworkArena arena;

    public ArenaResetEvent(NetworkArena arena){
        this.arena = arena;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public NetworkArena getArena() {
        return arena;
    }

    public void setArena(NetworkArena arena) {
        this.arena = arena;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
