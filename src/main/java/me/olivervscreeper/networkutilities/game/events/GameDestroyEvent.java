package me.olivervscreeper.networkutilities.game.events;

import lombok.AccessLevel;
import lombok.Getter;
import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author OliverVsCreeper
 */
public class GameDestroyEvent extends Event implements Cancellable{

    static HandlerList handlers = new HandlerList();
    Boolean cancelled = false;

    @Getter(AccessLevel.PUBLIC) private Game game;

    public GameDestroyEvent(Game game){
        this.game = game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean newStatus) {
        cancelled = newStatus;
    }

}
