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

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean wasForced(){
        return forced;
    }

}
