package me.olivervscreeper.networkutilities.game.events;

import org.bukkit.entity.Player;
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
public class PlayerDeathInArenaEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Setter @Getter private boolean cancelled;
    @Getter private Game game;
    @Getter private Player player;
    private boolean forced;

    public PlayerDeathInArenaEvent(Game game, Player player, boolean forced) {
        this.game = game;
        this.player = player;
        this.forced = forced;
        cancelled = false;
    }

    public boolean wasForced() {
        return forced;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
