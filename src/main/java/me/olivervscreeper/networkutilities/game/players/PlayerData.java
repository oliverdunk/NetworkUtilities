package me.olivervscreeper.networkutilities.game.players;

import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.nbtserialization.PlayerSerializer;
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
  byte[] data;

  GameMode gameMode;

  public PlayerData(String name) {
    this.name = name;
  }

  public PlayerData prepare() {
    Player player = Bukkit.getPlayer(name);
    data = NetworkUtilities.playerSerializer.serializePlayer(player, PlayerSerializer.PLAYER_DATA_KEYS, false);
    return this;
  }

  public PlayerData revert() {
    try {
      Player player = Bukkit.getPlayer(name);
      NetworkUtilities.playerSerializer.deserializePlayer(player, data, false);
      return this;
    } catch (Exception ex) {
      NetworkUtilities.logger
          .log("PlayerData", "Attempted to revert data of " + name + ", but failed.");
      return null;
    }
  }
}
