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
public class PlayerStopSpectatingGameEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Setter private boolean cancelled = false;
    private Game game;
    private Player player;
    private GameState gameState;

    public PlayerStopSpectatingGameEvent(Game game, Player player, GameState gameState) {
        this.game = game;
        this.player = player;
        this.gameState = gameState;

    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
