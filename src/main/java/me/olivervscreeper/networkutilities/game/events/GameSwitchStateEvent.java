package me.olivervscreeper.networkutilities.game.events;

import lombok.Getter;
import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author OliverVsCreeper
 */
public class GameSwitchStateEvent extends Event implements Cancellable{

    static HandlerList handlers = new HandlerList();
    Boolean cancelled = false;

    @Getter private Game game;
    @Getter private GameState newGameState;

    public GameSwitchStateEvent(Game game, GameState newGameState){
        this.game = game;
        this.newGameState = newGameState;
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
