package me.olivervscreeper.networkutilities.game.states;

import lombok.Getter;
import lombok.Setter;
import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.game.Game;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * @author OliverVsCreeper
 */
public abstract class GameState implements Listener {

  public Game gameInstance;
  @Getter @Setter private String name;
  @Getter @Setter private String displayName;

  /**
   * Default constructor for a GameState
   *
   * @param game Instance of the game which is handling the state
   * @param name Raw name of the state for display in log files
   * @param displayName Formatted display name for use in chat messages
   */
  public GameState(Game game, String name, String displayName) {
    this.gameInstance = game;
    this.name = name;
    this.displayName = displayName;
  }

  @Getter int runtime = 0;

  public void incrementRuntime() {
    runtime++;
  }

  //If either return false, a GameState change can not take place
  public abstract boolean onStateBegin();

  public abstract boolean onStateEnd();

  public abstract void tick();

  public void registerListener(Listener classInstance) {
    Bukkit.getPluginManager().registerEvents(classInstance, NetworkUtilities.plugin);
  }

  public void unregisterListener(Listener classInstance) {
    HandlerList.unregisterAll(classInstance);
  }

}