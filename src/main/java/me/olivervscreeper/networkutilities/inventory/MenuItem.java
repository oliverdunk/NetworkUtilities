package me.olivervscreeper.networkutilities.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by OliverVsCreeper on 29/01/2015.
 */
public abstract class MenuItem {

    String displayName;
    Material displayItem;
    List<String> lore;
    int data = 0;

    public MenuItem(String displayName, Material displayItem){
        this.displayName = displayName;
        this.displayItem = displayItem;
    }

    public MenuItem(String displayName, Material displayItem, int data){
        this.displayName = displayName;
        this.displayItem = displayItem;
        this.data = data;
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
        meta.setDisplayName(displayName);
        if(lore != null) meta.setLore(lore);
        stack.setItemMeta(meta);

        return stack;
    }

    public abstract void onClick(Player player);

}
