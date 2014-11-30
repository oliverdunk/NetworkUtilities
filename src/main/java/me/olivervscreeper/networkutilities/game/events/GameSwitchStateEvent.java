package me.olivervscreeper.networkutilities.game.events;

import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class GameSwitchStateEvent extends Event implements Cancellable{

    static HandlerList handlers = new HandlerList();
    Boolean cancelled = false;

    private Game game;
    private GameState newGameState;

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

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean newStatus) {
        cancelled = newStatus;
    }

    public Game getGame() {
        return game;
    }

    public GameState getNewGameState() {
        return newGameState;
    }
}
