package me.olivervscreeper.networkutilities.events.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class SignChangeHandler implements Listener{

    /**
     * Event handling information
     */
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public SignChangeHandler(EventPriority eventPriority){this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onSignChangeLowest(SignChangeEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onSignChangeLow(SignChangeEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onSignChangeNormal(SignChangeEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSignChangeHigh(SignChangeEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSignChangeHighest(SignChangeEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        event.setCancelled(true);
    }

}
