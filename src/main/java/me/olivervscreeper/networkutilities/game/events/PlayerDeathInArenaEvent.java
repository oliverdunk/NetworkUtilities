package me.olivervscreeper.networkutilities.game.events;

import lombok.AccessLevel;
import lombok.Getter;
import me.olivervscreeper.networkutilities.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author OliverVsCreeper
 */
public class PlayerDeathInArenaEvent extends Event{

    static HandlerList handlers = new HandlerList();
    boolean cancelled = false;

    @Getter(AccessLevel.PUBLIC) private Game game;
    @Getter(AccessLevel.PUBLIC) private Player player;
    private boolean forced;

    public PlayerDeathInArenaEvent(Game game, Player player, boolean forced){
        this.game = game;
        this.player = player;
        this.forced = forced;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean wasForced(){
        return forced;
    }

}
