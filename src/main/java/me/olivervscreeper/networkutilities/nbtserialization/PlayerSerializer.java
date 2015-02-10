/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.olivervscreeper.networkutilities.nbtserialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.cyberiantiger.minecraft.nbt.CompoundTag;
import org.cyberiantiger.minecraft.nbt.TagInputStream;
import org.cyberiantiger.minecraft.nbt.TagOutputStream;
import org.cyberiantiger.minecraft.unsafe.CBShim;
import org.cyberiantiger.minecraft.unsafe.NBTTools;

/**
 *
 * @author antony
 */
public class PlayerSerializer {
    private NBTTools tools;

    public static final Set<String> PLAYER_DATA_KEYS;

    static {
        Set<String> playerDataKeys = new HashSet<String>();
        // See http://minecraft.gamepedia.com/Player.dat_format
        // This list needs some love.
        playerDataKeys.add("Inventory");
        playerDataKeys.add("EnderItems");
        playerDataKeys.add("abilities");
        PLAYER_DATA_KEYS = Collections.unmodifiableSet(playerDataKeys);
    }

    /**
     * Create a new InventorySerializer
     * 
     * @param plugin 
     * @throws UnsupportedOperationException If the version of craftbukkit is not supported.
     */
    public PlayerSerializer(Plugin plugin) {
        tools = CBShim.createShim(NBTTools.class, plugin);
    }

    /**
     * Deserialize an array of items, from a byte array.
     * 
     * @param player the player to load the data into
     * @param data the data to deserialize
     * @param compress If true, gzip decompress the data
     * @return the ItemStack array.
     * @throws IllegalStateException If there is an error reading the data.
     */
    public void deserializePlayer(Player player, byte[] data, boolean compress) {
        ByteArrayInputStream inRaw = new ByteArrayInputStream(data);
        try {
            InputStream in;
            if (compress) {
                in = new GZIPInputStream(inRaw);
            } else {
                in = inRaw;
            }
            TagInputStream tagIn = new TagInputStream(in);
            CompoundTag playerData = (CompoundTag) tagIn.readTag();
            tagIn.close();
            tools.updateEntity(player, playerData);
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        } catch (ClassCastException cce) {
            throw new IllegalStateException(cce);
        }
    }

    /**
     * Serialize a player's data.
     * 
     * @param player The player to serialize
     * @param keys The nbt keys to serialize
     * @param compress If true, compress the data
     * @return the serialized data
     * @throws IllegalStateException if any errors occur
     */
    public byte[] serializePlayer(Player player, Set<String> keys, boolean compress) {
        ByteArrayOutputStream outRaw = new ByteArrayOutputStream();
        try {
            OutputStream out;
            if (compress) {
                out = new GZIPOutputStream(outRaw);
            } else {
                out = outRaw;
            }
            TagOutputStream tagOut = new TagOutputStream(out);
            CompoundTag playerData = tools.readEntity(player);
            playerData.getValue().keySet().retainAll(keys);
            tagOut.writeTag(playerData);
            tagOut.close();
            return outRaw.toByteArray();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}