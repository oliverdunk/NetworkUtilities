package me.olivervscreeper.networkutilities.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author OliverVsCreeper
 */
public abstract class MenuItem {

    String displayName;
    Material displayItem;
    List<String> lore;
    int data = 0;

    public MenuItem(ItemStack item) {
        this.displayName = ((item.hasItemMeta() && item.getItemMeta().hasDisplayName()) ? item.getItemMeta().getDisplayName() : null);
        this.displayItem = item.getType();
        this.lore = ((item.hasItemMeta() && item.getItemMeta().hasLore()) ? item.getItemMeta().getLore() : null);
        this.data = item.getDurability();
    }

    public MenuItem(String displayName, Material displayItem){
        this.displayName = displayName;
        this.displayItem = displayItem;
    }

    public MenuItem(String displayName, Material displayItem, int data){
        this.displayName = displayName;
        this.displayItem = displayItem;
        this.data = data;
    }

    public MenuItem(String displayName, Material displayItem, int data, String... lore){
        this.displayName = displayName;
        this.displayItem = displayItem;
        this.data = data;
        this.lore = Arrays.asList(lore);
    }

    public MenuItem(String displayName, Material displayItem, int data, List<String> lore){
        this.displayName = displayName;
        this.displayItem = displayItem;
        this.data = data;
        this.lore = lore;
    }

    public ItemStack constructItem(){
        ItemStack stack = new ItemStack(displayItem, 1, (byte) data);

        ItemMeta meta = stack.getItemMeta();
        if(displayName != null) meta.setDisplayName(displayName);
        if(lore != null) meta.setLore(lore);
        stack.setItemMeta(meta);

        return stack;
    }

    public abstract void onClick(Player player);

}
