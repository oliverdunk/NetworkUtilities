package me.olivervscreeper.networkutilities.events.player;

import me.olivervscreeper.networkutilities.permissions.PermissionSet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created on 25/11/2014.
 *
 * @author OliverVsCreeper
 */
public class PlayerMoveHandler implements Listener{

    /**
     * Event handling information
     */
    PermissionSet permission;
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public PlayerMoveHandler(PermissionSet permission){this.permission = permission;}
    public PlayerMoveHandler(PermissionSet permission, EventPriority eventPriority){this.permission = permission; this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMoveLowest(PlayerMoveEvent event){
        if(!eventPriority.equals(eventPriority.LOWEST)) return;
        if(permission.playerHasPermission(event.getPlayer())) return;
        if(!(event.getTo().distance(event.getFrom()) >= 1)) return;
        event.setCancelled(true);    }

    @EventHandler(priority = EventPriority.LOW)
    public void onMoveLow(PlayerMoveEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        if(permission.playerHasPermission(event.getPlayer())) return;
        if(!(event.getTo().distance(event.getFrom()) >= 1)) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMoveNormal(PlayerMoveEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        if(permission.playerHasPermission(event.getPlayer())) return;
        if(!(event.getTo().distance(event.getFrom()) >= 1)) return;
        event.setCancelled(true);    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onMoveHigh(PlayerMoveEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        if(permission.playerHasPermission(event.getPlayer())) return;
        if(!(event.getTo().distance(event.getFrom()) >= 1)) return;
        event.setCancelled(true);    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMoveHighest(PlayerMoveEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        if(permission.playerHasPermission(event.getPlayer())) return;
        if(!(event.getTo().distance(event.getFrom()) >= 1)) return;
        event.setCancelled(true);    }

}
