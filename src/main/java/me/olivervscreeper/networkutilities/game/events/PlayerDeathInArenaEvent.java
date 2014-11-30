package me.olivervscreeper.networkutilities.game.events;

import me.olivervscreeper.networkutilities.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class PlayerDeathInArenaEvent extends Event{

    static HandlerList handlers = new HandlerList();
    Boolean cancelled = false;

    private Game game;
    private Player player;

    public PlayerDeathInArenaEvent(Game game, Player player){
        this.game = game;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

}
