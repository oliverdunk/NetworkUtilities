package me.olivervscreeper.networkutilities.game.events;

import org.bukkit.entity.Player;
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
public class PlayerJoinGameEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Setter private boolean cancelled;
    private Game game;
    private Player player;
    private GameState gameState;

    public PlayerJoinGameEvent(Game game, Player player, GameState gameState) {
        this.game = game;
        this.player = player;
        this.gameState = gameState;
        cancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
