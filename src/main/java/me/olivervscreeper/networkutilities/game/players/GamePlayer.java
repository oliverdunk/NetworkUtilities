package me.olivervscreeper.networkutilities.game.players;

import me.olivervscreeper.networkutilities.game.Game;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class GamePlayer {

  String name;
  Game game;
  PlayerData data;

  public GamePlayer(String name, Game game) {
    this.name = name;
    this.game = game;
  }

  public Player getPlayer() {
    return Bukkit.getPlayerExact(name);
  }

  public void saveData() {
    data = new PlayerData(getPlayer().getName()).prepare();
    game.getLogger().log("GamePlayer", "Player data for " + name + " has been saved");
  }

  public void resetData() {
    data.revert();
    game.getLogger().log("GamePlayer", "Player data for " + name + " has been reverted");
  }

  //Completely wipes a player of everything
  public void reset() {
    Player p = getPlayer();
    p.getInventory().clear();
    p.getInventory().setArmorContents(null);
    p.setTotalExperience(0);
    getPlayer().setExp(0F);
    p.setHealth(20D);
    p.setMaxHealth(20D);
    p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    p.setAllowFlight(false);
    p.resetPlayerTime();
    p.resetPlayerWeather();
    p.setFoodLevel(20);
    p.getEnderChest().clear();
    for (PotionEffect effect : p.getActivePotionEffects()) {
      p.removePotionEffect(effect.getType());
    }
  }

}
