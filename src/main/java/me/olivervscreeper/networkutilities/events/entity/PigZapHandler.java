package me.olivervscreeper.networkutilities.events.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PigZapEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class PigZapHandler implements Listener{

    /**
     * Event handling information
     */
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public PigZapHandler(EventPriority eventPriority){this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPigZapLowest(PigZapEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPigZapLow(PigZapEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPigZapNormal(PigZapEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPigZapHigh(PigZapEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPigZapHighest(PigZapEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        event.setCancelled(true);
    }

}
