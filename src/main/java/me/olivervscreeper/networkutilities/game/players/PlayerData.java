package me.olivervscreeper.networkutilities.game.players;

import me.olivervscreeper.networkutilities.serialization.LocationSerialization;
import me.olivervscreeper.networkutilities.serialization.PlayerSerialization;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class PlayerData {

    String name;
    String serializedPlayer;
    String serializedLocation;

    public PlayerData(String name){
        this.name = name;
    }

    public PlayerData prepare(){
        Player player = Bukkit.getPlayer(name);
        serializedPlayer = PlayerSerialization.serializePlayerAsString(player);
        serializedLocation = LocationSerialization.serializeLocationAsString(player.getLocation());
        return this;
    }

    public PlayerData revert(){
        Player player = Bukkit.getPlayer(name);
        PlayerSerialization.setPlayer(serializedPlayer, player);
        player.teleport(LocationSerialization.getLocationMeta(serializedLocation));
        return this;
    }


}
