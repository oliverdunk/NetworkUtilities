package me.olivervscreeper.networkutilities.events;

import me.olivervscreeper.networkutilities.permissions.PermissionSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created on 27/11/2014.
 *
 * @author OliverVsCreeper
 */
public class PVPEventHandler {

    /**
     * Event handling information
     */
    PermissionSet permission;
    EventPriority eventPriority = EventPriority.NORMAL;

    /*
    Constructors for the event class
     */
    public PVPEventHandler(PermissionSet permission){this.permission = permission;}
    public PVPEventHandler(PermissionSet permission, EventPriority eventPriority){this.permission = permission; this.eventPriority = eventPriority;}

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPVPLowest(EntityDamageByEntityEvent event){
        if (!eventPriority.equals(eventPriority.LOWEST)) return;
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)) return;
        if(permission.playerHasPermission((Player) event.getDamager())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPVPLow(EntityDamageByEntityEvent event) {
        if (!eventPriority.equals(eventPriority.LOW)) return;
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)) return;
        if(permission.playerHasPermission((Player) event.getDamager())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPVPNormal(EntityDamageByEntityEvent event){
        if(!eventPriority.equals(eventPriority.NORMAL)) return;
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)) return;
        if(permission.playerHasPermission((Player) event.getDamager())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPVPHigh(EntityDamageByEntityEvent event){
        if(!eventPriority.equals(eventPriority.HIGH)) return;
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)) return;
        if(permission.playerHasPermission((Player) event.getDamager())) return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPVPHighest(EntityDamageByEntityEvent event){
        if(!eventPriority.equals(eventPriority.HIGHEST)) return;
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)) return;
        if(permission.playerHasPermission((Player) event.getDamager())) return;
        event.setCancelled(true);
    }

}
