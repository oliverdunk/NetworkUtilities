package me.olivervscreeper.networkutilities.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.olivervscreeper.networkutilities.NetworkUtilities;

/**
 * @author OliverVsCreeper
 */
public class Menu implements Listener {

    private Inventory inventory;
    private String title;
    private int rows;
    private HashMap<Integer, MenuItem> items = new HashMap<Integer, MenuItem>();
    private List<UUID> hasOpen = new ArrayList<UUID>();
    private boolean closeOnClick = true;

    public Menu(String title, int rows) {
        this.title = title;
        this.rows = rows;
        Bukkit.getPluginManager().registerEvents(this, NetworkUtilities.plugin);
    }

    public Menu(String title, int rows, boolean closeOnClick) {
        this.title = title;
        this.rows = rows;
        this.closeOnClick = closeOnClick;
        Bukkit.getPluginManager().registerEvents(this, NetworkUtilities.plugin);
    }

    public void addItem(MenuItem item, int slot) {
        items.put(slot, item);
    }

    public void open(Player player) {
        inventory = Bukkit.createInventory(null, rows * 9, title);
        for (int slot : items.keySet()) {
            inventory.setItem(slot, items.get(slot).constructItem());
        }
        player.openInventory(inventory);
        hasOpen.add(player.getUniqueId());
    }

    public void update() {
        for (int slot : items.keySet()) {
            inventory.setItem(slot, items.get(slot).constructItem());
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!event.getInventory().getTitle().equals(title)) return;
        if (!hasOpen.contains(event.getWhoClicked().getUniqueId())) return;
        event.setCancelled(true);
        if (items.get(event.getSlot()) == null) return;
        if (closeOnClick) {
            Bukkit.getScheduler().runTaskLater(NetworkUtilities.plugin,
                    new Runnable() {
                        public void run() {
                            event.getWhoClicked().closeInventory();
                        }
                    }, 1);
            hasOpen.remove(event.getWhoClicked().getUniqueId());
        }
        items.get(event.getSlot()).onClick((Player) event.getWhoClicked());

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (hasOpen.contains(player.getUniqueId())) hasOpen.remove(player.getUniqueId());
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
