package me.olivervscreeper.networkutilities.game.events;

import me.olivervscreeper.networkutilities.game.NetworkArena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created on 28/11/2014.
 *
 * @author OliverVsCreeper
 */
public class PlayerJoinArenaEvent extends Event{

    private static HandlerList handlers = new HandlerList();

    private NetworkArena arena;
    private Player player;
    private Boolean forced;

    public PlayerJoinArenaEvent(boolean forced, Player player, NetworkArena arena){
        this.forced = forced;
        this.player = player;
        this.arena = arena;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public NetworkArena getArena() {
        return arena;
    }

    public void setArena(NetworkArena arena) {
        this.arena = arena;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Boolean getForced() {
        return forced;
    }

    public void setForced(Boolean forced) {
        this.forced = forced;
    }
}
