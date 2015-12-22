package me.olivervscreeper.networkutilities.game.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;

/**
 * @author OliverVsCreeper
 */
@Getter
public class GameSwitchStateEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Setter private boolean cancelled;
    private Game game;
    private GameState newGameState;

    public GameSwitchStateEvent(Game game, GameState newGameState) {
        this.game = game;
        this.newGameState = newGameState;
        cancelled = false;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
