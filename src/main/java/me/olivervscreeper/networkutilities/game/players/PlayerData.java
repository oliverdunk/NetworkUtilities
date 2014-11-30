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

    String UUID;
    String serializedPlayer;
    String serializedLocation;

    public PlayerData prepare(){
        Player player = Bukkit.getPlayer(UUID);
        serializedPlayer = PlayerSerialization.serializePlayerAsString(player);
        serializedLocation = LocationSerialization.serializeLocationAsString(player.getLocation());
        return this;
    }

    public PlayerData revert(){
        Player player = Bukkit.getPlayer(UUID);
        PlayerSerialization.setPlayer(serializedPlayer, player);
        player.teleport(LocationSerialization.getLocationMeta(serializedLocation));
        return this;
    }


}
