package me.olivervscreeper.networkutilities.game.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.olivervscreeper.networkutilities.game.Game;

/**
 * @author OliverVsCreeper
 */
@AllArgsConstructor
public class GameCreateEvent extends Event {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Getter private Game game;

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
