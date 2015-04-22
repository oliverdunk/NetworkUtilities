package me.olivervscreeper.networkutilities.game;


import me.olivervscreeper.networkutilities.NULogger;
import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.game.events.*;
import me.olivervscreeper.networkutilities.game.extensions.GameExtension;
import me.olivervscreeper.networkutilities.game.players.GamePlayer;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.game.states.IdleGameState;

import me.olivervscreeper.networkutilities.messages.Message;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 29/11/2014.
 *
 * @author OliverVsCreeper
 */
public abstract class Game implements Listener {

  GameState currentState = null; //State of the Game
  Iterator stateIterator;
  private NULogger logger;
  private String rawName;
  private String name;

  public HashMap<String, GamePlayer> players = new HashMap<String, GamePlayer>();
  public HashMap<String, GamePlayer> spectators = new HashMap<String, GamePlayer>();

  public abstract List<GameState> getAllStates();

  public abstract Location getLobbyLocation();

  public String getName(){
    return name;
  }

  public String getRawName(){
    return rawName;
  }

  /**
   * Default constructor for any Game instance
   *
   * @param logger Logger in which to write game debug information - cannot be null
   */
  public Game(NULogger logger, String rawName, String name) {
    this.logger = logger;
    this.rawName = rawName;
    this.name = name;
    logger.log("Game", rawName + " has been initialized");
    Bukkit.getPluginManager().registerEvents(this, NetworkUtilities.plugin);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(NetworkUtilities.plugin,
                                                    new Runnable() {
                                                      @Override
                                                      public void run() {
                                                        tick();
                                                      }
                                                    }, 0, 20);
    logger.log("Game", "Events registered and ticks scheduled");
    GameCreateEvent event = new GameCreateEvent(this);
    Bukkit.getPluginManager().callEvent(event);
  }

  /**
   * @return Debug logger in use by the Game instance
   */
  public NULogger getLogger() {
    return logger;
  }

  /**
   * Require the Game instance to use a GameExtensions, and trigger it to be loaded immediately.
   *
   * @param extension GameExtension that is required
   * @return If the GameExtension has reported a successful initialization
   */
  public boolean requireExtension(GameExtension extension) {
    logger.log("Game", "Attempting to load extension " + extension.getName());
    return extension.onEnable();
  }

  /**
   * @return Returns the state of the Game instance
   */
  public GameState getState() {
    return currentState;
  }

  /**
   * Calls the GameSwitchStateEvent. If this is successful, a state switch is attempted. However,
   * both the current state and next state could end this by returning false on initialization or
   * unloading
   *
   * @param state The state in which the game should become
   * @return If the state change was successful
   */
  @Deprecated //Deprecated to stop usage instead of nextState()
  public boolean setState(GameState state) {
    logger.log("Game", "Attempting to set state to " + state.getName());
    if (currentState != null) {
      //Throw the linked event, and end the action if the event becomes cancelled
      GameSwitchStateEvent event = new GameSwitchStateEvent(this, state);
      Bukkit.getPluginManager().callEvent(event);
      if (event.isCancelled()) {
        return false;
      }

      if (!currentState.onStateEnd()) {
        return false;
      }
    }
    if (!state.onStateBegin()) {
      return false;
    }
    currentState = state;
    logger.log("Game", "State changed to " + getState().getName());
    return true;
  }

  /**
   * Simple tick method used in a one second interval. If a state is not currently loaded, a state
   * change is attempted. The tick method inside each state is also called here
   */
  public void tick() {
    if (currentState == null) {
      nextState();
    }
    currentState.tick();
    currentState.incrementRuntime();
  }

  /**
   * Rotates the state of the Game instance using an iterator, which is recreated and looped if no
   * more states are available
   * @return If the state change was successful
   */
  public boolean nextState() {
    if (stateIterator == null) {
      stateIterator = getAllStates().iterator();
    }
    if (!stateIterator.hasNext()) {
      stateIterator = getAllStates().iterator();
    }
    return setState((GameState) stateIterator.next()); //Set state to the next possible state
  }

  /**
   * Attempts to add a player to the game. If the PlayerJoinGameEvent is cancelled, the player is
   * not added to the game. If it succeeds, the player is made into a new GamePlayer instance and
   * their PlayerData reset. They are set to Adventure mode and if it is available, moved to
   * the specified lobbyLocation
   *
   * @param player The player to add to the game
   * @return If the player was successfully added to the game
   */
  public boolean addPlayer(Player player) {
    if (players.containsKey(player.getName())) {
      return false;
    }
    //Throw the linked event, and end the action if the event becomes cancelled
    PlayerJoinGameEvent event = new PlayerJoinGameEvent(this, player, currentState);
    Bukkit.getPluginManager().callEvent(event);
    if (event.isCancelled()) {
      return false;
    }

    players.put(player.getName(), new GamePlayer(player.getName(), this));
    players.get(player.getName()).saveData();
    players.get(player.getName()).reset();
    player.setGameMode(GameMode.ADVENTURE);
    logger.log("Game", "Player " + player.getName() + " was added to the game");
    if (getLobbyLocation() == null) {
      return true;
    }
    player.teleport(getLobbyLocation());
    return true;
  }

  /**
   * Attempts to remove a player from the game. If the PlayerLeaveGameEvent is cancelled, the player
   * is not removed from the game. The player is reverted to their original PlayerData, and this is
   * logged.
   * @param player The player to remove from the game
   * @return If the player was successfully removed from the game
   */
  public boolean removePlayer(Player player) {
    //Throw the linked event, and end the action if the event becomes cancelled
    PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(this, player, currentState);
    Bukkit.getPluginManager().callEvent(event);
    if (event.isCancelled()) {
      return false;
    }

    players.get(player.getName()).resetData();
    players.remove(player.getName());
    logger.log("Game", "Player " + player.getName() + " was removed from the game");
    return true;
  }

  /**
   * Attempts to add a spectator the game. If the PlayerStartSpectatingGameEvent is cancelled, the
   * player is not changed to a spectator. The player is also moved the the lobbyLocation if it
   * is specified
   *
   * @param player The player to add to the game
   * @return If the player was successfully set to a spectator
   */
  public boolean addSpectator(Player player) {
    //Throw the linked event, and end the action if the event becomes cancelled
    PlayerStartSpectatingGameEvent event =
        new PlayerStartSpectatingGameEvent(this, player, currentState);
    Bukkit.getPluginManager().callEvent(event);
    if (event.isCancelled()) {
      return false;
    }
    spectators.put(player.getName(), new GamePlayer(player.getName(), this));
    spectators.get(player.getName()).saveData();
    player.setGameMode(GameMode.SPECTATOR);
    logger.log("Game", "Player " + player.getName() + " is now spectating");
    if (getLobbyLocation() == null) {
      return true;
    }
    player.teleport(getLobbyLocation());
    return true;
  }

  /**
   * Attempts to remove a spectator from the game. If the PlayerStopSpectatingEvent is cancelled,
   * the player is not stopped from spectating. PlayerData is also reverted during this
   *
   * @param player The player to add to the game
   * @return If the player was successfully stopped from spectating
   */
  public boolean removeSpectator(Player player) {
    //Throw the linked event, and end the action if the event becomes cancelled
    PlayerStopSpectatingGameEvent event =
        new PlayerStopSpectatingGameEvent(this, player, currentState);
    Bukkit.getPluginManager().callEvent(event);
    if (event.isCancelled()) {
      return false;
    }
    spectators.get(player.getName()).resetData();
    spectators.remove(player.getName());
    logger.log("Game", "Player " + player.getName() + " is no longer spectating");
    return true;
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onEntityDamage(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    Player player = (Player) event.getEntity();
    if ((players.keySet().contains(player.getName()))) {
      if (!((player.getHealth() - event.getDamage() <= 0))) {
        return;
      }
      event.setCancelled(true);
      player.setHealth(20D);
      players.get(player.getName()).addDeath();

      //Throw the linked event - cannot be cancelled
      PlayerDeathInArenaEvent newEvent = new PlayerDeathInArenaEvent(this, player, false);
      Bukkit.getPluginManager().callEvent(newEvent);
    } else if (spectators.keySet().contains(player.getName())) {
      event.setCancelled(true);
      player.setHealth(20D);
      player.teleport(getLobbyLocation());
    }
  }

  /**
   * Forcefully kills a player with a simulation of the usual mechanics of the game. Also triggers the
   * PlayerDeathInArenaEvent as if the death was usual.
   *
   * @param player the player to kill
   */
  public void killPlayer(Player player){
    if(players.containsKey(player.getName())){
      player.setHealth(20D);
      player.teleport(getLobbyLocation());
      players.get(player.getName()).addDeath();

      //Throw the linked event - cannot be cancelled
      PlayerDeathInArenaEvent newEvent = new PlayerDeathInArenaEvent(this, player, true);
      Bukkit.getPluginManager().callEvent(newEvent);
      logger.log("Game", "Forcefully killed " + player.getName() + ".");
    }else{
        logger.log("Game", "Attempted to kill " + player.getName() + ", but the player was not playing.");
    }
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    if (!players.containsKey(event.getPlayer().getName())) {
      return;
    }
    removePlayer(event.getPlayer());
  }

  /**
   * Calls the destroy method without force.
   */
  public void destroy(){
    destroy(false);
  }

  /**
   * Safely removes all players from a game and removes any listeners or tasks running inside of it.
   * Useful for the deletion of games in large setups.
   *
   * @param force If the event should be cancellable
   */
  public void destroy(boolean force){
    logger.log("Game", "Beginning " + (force ? "forceful" : "soft") + " destroy sequence...");
    GameDestroyEvent event = new GameDestroyEvent(this);
    if(!force) Bukkit.getPluginManager().callEvent(event);
    if(event.isCancelled()){
      logger.log("Game", "Destroy sequence cancelled.");
      return;
    }
    for(GamePlayer player : players.values()){
      player.resetData();
      new Message(Message.WARNING).addRecipient(player).send("The game you were in was removed.");
    }
    logger.log("Game", "Player data reset.");
    HandlerList.unregisterAll(this);
    logger.log("Game", "Events unregistered.");
    logger.log("Game", "Game successfully destroyed.");
  }

}
