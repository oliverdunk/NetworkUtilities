package me.olivervscreeper.networkutilities.events.entity;

import me.olivervscreeper.networkutilities.permissions.PermissionSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class FoodLevelChangeHandler implements Listener{

    /**
     * Event handling information
     */
    PermissionSet permission;
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public FoodLevelChangeHandler(PermissionSet permission){this.permission = permission;}
    public FoodLevelChangeHandler(PermissionSet permission, EventPriority eventPriority){this.permission = permission; this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFoodLevelChangeLowest(FoodLevelChangeEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        if(permission.playerHasPermission((Player) event.getEntity())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onFoodLevelChangeLow(FoodLevelChangeEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        if(permission.playerHasPermission((Player) event.getEntity())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFoodLevelChangeNormal(FoodLevelChangeEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        if(permission.playerHasPermission((Player) event.getEntity())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFoodLevelChangeHigh(FoodLevelChangeEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        if(permission.playerHasPermission((Player) event.getEntity())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFoodLevelChangeHighest(FoodLevelChangeEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        if(permission.playerHasPermission((Player) event.getEntity())) return;
        event.setCancelled(true);
    }

}
