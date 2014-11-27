package me.olivervscreeper.networkutilities.events.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class InventoryPickupItemHandler implements Listener{

    /**
     * Event handling information
     */
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public InventoryPickupItemHandler(EventPriority eventPriority){this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryPickupItemLowest(InventoryPickupItemEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryPickupItemLow(InventoryPickupItemEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryPickupItemNormal(InventoryPickupItemEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryPickupItemHigh(InventoryPickupItemEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryPickupItemHighest(InventoryPickupItemEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        event.setCancelled(true);
    }

}
