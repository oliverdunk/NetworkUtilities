package me.olivervscreeper.networkutilities.game.events;

import lombok.Getter;
import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author OliverVsCreeper
 */
public class GameCreateEvent extends Event{

    static HandlerList handlers = new HandlerList();

    @Getter private Game game;

    public GameCreateEvent(Game game){
        this.game = game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
