package me.olivervscreeper.networkutilities.events.entity;

import me.olivervscreeper.networkutilities.permissions.PermissionSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class PlayerLeashEntityHandler implements Listener{

    /**
     * Event handling information
     */
    PermissionSet permission;
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public PlayerLeashEntityHandler(PermissionSet permission, EventPriority eventPriority){this.permission = permission; this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLeashEntityLowest(PlayerLeashEntityEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerLeashEntityLow(PlayerLeashEntityEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLeashEntityNormal(PlayerLeashEntityEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLeashEntityHigh(PlayerLeashEntityEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeashEntityHighest(PlayerLeashEntityEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        if(permission.playerHasPermission((Player) event.getPlayer())) return;
        event.setCancelled(true);
    }

}
