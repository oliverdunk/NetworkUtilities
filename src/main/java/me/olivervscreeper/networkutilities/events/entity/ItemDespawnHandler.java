package me.olivervscreeper.networkutilities.events.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class ItemDespawnHandler implements Listener{

    /**
     * Event handling information
     */
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public ItemDespawnHandler(EventPriority eventPriority){this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemDespawnLowest(ItemDespawnEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onItemDespawnLow(ItemDespawnEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onItemDespawnNormal(ItemDespawnEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onItemDespawnHigh(ItemDespawnEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDespawnHighest(ItemDespawnEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        event.setCancelled(true);
    }

}
