package me.olivervscreeper.networkutilities.events.hanging;

import me.olivervscreeper.networkutilities.permissions.PermissionSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class HangingPlaceHandler implements Listener{

    /**
     * Event handling information
     */
    PermissionSet permission;
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public HangingPlaceHandler(PermissionSet permission){this.permission = permission;}
    public HangingPlaceHandler(PermissionSet permission, EventPriority eventPriority){this.permission = permission; this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onHangingPlaceLowest(HangingPlaceEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onHangingPlaceLow(HangingPlaceEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onHangingPlaceNormal(HangingPlaceEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHangingPlaceHigh(HangingPlaceEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHangingPlaceHighest(HangingPlaceEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

}
