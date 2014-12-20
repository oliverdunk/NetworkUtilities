package me.olivervscreeper.networkutilities.game.players;

import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.serialization.LocationSerialization;
import me.olivervscreeper.networkutilities.serialization.PlayerSerialization;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

    GameMode gameMode;

    public PlayerData(String name){
        this.name = name;
    }

    public PlayerData prepare(){
        Player player = Bukkit.getPlayer(name);
        serializedPlayer = PlayerSerialization.serializePlayerAsString(player);
        serializedLocation = LocationSerialization.serializeLocationAsString(player.getLocation());
        gameMode = player.getGameMode();
        return this;
    }

    public PlayerData revert(){
        try {
            Player player = Bukkit.getPlayer(name);
            PlayerSerialization.setPlayer(serializedPlayer, player);
            player.teleport(LocationSerialization.getLocationMeta(serializedLocation));
            player.setGameMode(gameMode);
            return this;
        }catch (Exception ex){
            NetworkUtilities.logger.log("PlayerData", "Attempted to revert data of " + name + ", but failed.");
            return null;
        }
    }


}
