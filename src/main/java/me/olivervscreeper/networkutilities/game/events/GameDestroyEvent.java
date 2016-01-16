package me.olivervscreeper.networkutilities.game.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.olivervscreeper.networkutilities.game.Game;

/**
 * @author OliverVsCreeper
 */
@Getter
public class GameDestroyEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Setter private boolean cancelled = false;
    private Game game;

    public GameDestroyEvent(Game game) {
        this.game = game;
        cancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
