package me.olivervscreeper.networkutilities.game;

import gnu.trove.map.hash.THashMap;
import me.olivervscreeper.networkutilities.NULogger;
import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.game.events.*;
import me.olivervscreeper.networkutilities.game.extensions.GameExtension;
import me.olivervscreeper.networkutilities.game.players.GamePlayer;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.game.states.IdleGameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
public abstract class Game implements Listener{

    //TODO: Add basic system for Arenas

    GameState currentState = null; //State of the Game
    Iterator stateIterator;
    private NULogger logger;

    public HashMap<String, GamePlayer> players = new HashMap<String, GamePlayer>();
    public HashMap<String, GamePlayer> spectators = new HashMap<String, GamePlayer>();

    public abstract String getName();
    public abstract List<GameState> getAllStates();
    public abstract Location getLobbyLocation();

    public Game(NULogger logger){
        this.logger = logger;
        logger.log("Game", getRawName() + " has been initialized");
        Bukkit.getPluginManager().registerEvents(this, NetworkUtilities.plugin);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(NetworkUtilities.plugin,
                new Runnable() {
                    @Override
                    public void run() {
                        tick();
                    }
                }, 0, 20);
        logger.log("Game", "Events registered and ticks scheduled");
    }

    public NULogger getLogger(){return logger;}

    protected abstract String getRawName();

    public boolean requireExtension(GameExtension extension){
        logger.log("Game", "Attempting to load extension " + extension.getName());
        return extension.onEnable();
    }

    public GameState getState(){return currentState;}

    @Deprecated //Deprecated to stop usage instead of nextState()
    public Boolean setState(GameState state){
        logger.log("Game", "Attempting to set state to " + state.getName());
        if(currentState != null) {
            //Throw the linked event, and end the action if the event becomes cancelled
            GameSwitchStateEvent event = new GameSwitchStateEvent(this, state);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return false;

            if (!currentState.onStateEnd()) return false;
        }
        if(!state.onStateBegin()) return false;
        currentState = state;
        logger.log("Game", "State changed to " + getState().getName());
        return true;
    }

    public void tick(){
        if(currentState == null) nextState();
        currentState.tick();
        currentState.incrementRuntime();
    }

    public Boolean nextState(){
        if(stateIterator == null){
            stateIterator = getAllStates().iterator();
        }
        if(!stateIterator.hasNext()){
            stateIterator = getAllStates().iterator();
        }
        return setState((GameState) stateIterator.next()); //Set state to the next possible state
    }

    public Boolean addPlayer(Player player){
        if(players.containsKey(player.getName())) return false;
        //Throw the linked event, and end the action if the event becomes cancelled
        PlayerJoinGameEvent event = new PlayerJoinGameEvent(this, player, currentState);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return false;

        players.put(player.getName(), new GamePlayer(player.getName(), this));
        players.get(player.getName()).saveData();
        players.get(player.getName()).reset();
        player.setGameMode(GameMode.ADVENTURE);
        logger.log("Game", "Player " + player.getName() + " was added to the game");
        if (getLobbyLocation() == null) return true;
        player.teleport(getLobbyLocation());
        return true;
    }

    public Boolean removePlayer(Player player){
        //Throw the linked event, and end the action if the event becomes cancelled
        PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(this, player, currentState);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return false;

        players.get(player.getName()).resetData();
        players.remove(player.getName());
        logger.log("Game", "Player " + player.getName() + " was removed from the game");
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        return true;
    }

    public Boolean addSpectator(Player player){
        //Throw the linked event, and end the action if the event becomes cancelled
        PlayerStartSpectatingGameEvent event = new PlayerStartSpectatingGameEvent(this, player, currentState);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return false;
        spectators.put(player.getName(), new GamePlayer(player.getName(), this));
        spectators.get(player.getName()).saveData();
        player.setGameMode(GameMode.SPECTATOR);
        logger.log("Game", "Player " + player.getName() + " is now spectating");
        if (getLobbyLocation() == null) return true;
        player.teleport(getLobbyLocation());
        return true;
    }

    public Boolean removeSpectator(Player player){
        //Throw the linked event, and end the action if the event becomes cancelled
        PlayerStopSpectatingGameEvent event = new PlayerStopSpectatingGameEvent(this, player, currentState);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return false;
        spectators.get(player.getName()).resetData();
        spectators.remove(player.getName());
        logger.log("Game", "Player " + player.getName() + " is no longer spectating");
        return true;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if((players.keySet().contains(player.getName()))) {
            if (!((player.getHealth() - event.getDamage() <= 0))) return;
            event.setCancelled(true);
            player.setHealth(20D);

            //Throw the linked event - cannot be cancelled
            PlayerDeathInArenaEvent newEvent = new PlayerDeathInArenaEvent(this, player);
            Bukkit.getPluginManager().callEvent(newEvent);
        }else if(spectators.keySet().contains(player.getName())){
            event.setCancelled(true);
            player.setHealth(20D);
            player.teleport(getLobbyLocation());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if(!players.containsKey(event.getPlayer().getName())) return;
        removePlayer(event.getPlayer());
    }

}
