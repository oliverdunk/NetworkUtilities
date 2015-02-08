package me.olivervscreeper.networkutilities.inventory;

import me.olivervscreeper.networkutilities.NetworkUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by OliverVsCreeper on 29/01/2015.
 */
public class Menu implements Listener{

    String title;
    int rows;
    HashMap<Integer, MenuItem> items = new HashMap<Integer, MenuItem>();
    List<Player> hasOpen = new ArrayList<Player>();
    boolean closeOnClick = true;

    public Menu(String title, int rows){
        this.title = title;
        this.rows = rows;
        Bukkit.getPluginManager().registerEvents(this, NetworkUtilities.plugin);
    }

    public Menu(String title, int rows, boolean closeOnClick){
        this.title = title;
        this.rows = rows;
        this.closeOnClick = closeOnClick;
        Bukkit.getPluginManager().registerEvents(this, NetworkUtilities.plugin);
    }

    public void addItem(MenuItem item, int slot){
        items.put(slot, item);
    }

    public void open(Player player){
        Inventory inventory = Bukkit.createInventory(null, rows * 9, title);
        for(int slot : items.keySet()){
            inventory.setItem(slot, items.get(slot).constructItem());
        }
        player.openInventory(inventory);
        hasOpen.add(player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!event.getInventory().getTitle().equals(title)) return;
        if(!hasOpen.contains(event.getWhoClicked())) return;
        event.setCancelled(true);
        if(items.get(event.getSlot()) == null) return;
        if(closeOnClick) event.getWhoClicked().closeInventory();

        items.get(event.getSlot()).onClick((Player) event.getWhoClicked());
        hasOpen.remove(event.getWhoClicked());
    }

    public void unregister(){
        HandlerList.unregisterAll(this);
    }

}
