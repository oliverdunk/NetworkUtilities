package me.olivervscreeper.networkutilities.game;

import gnu.trove.map.hash.THashMap;
import me.olivervscreeper.networkutilities.game.players.GamePlayer;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.game.states.IdleGameState;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 29/11/2014.
 *
 * @author OliverVsCreeper
 */
public abstract class Game {

    //TODO: Allow players to join, and create events for Game Systems
    //TODO: Add basic system for Arenas

    GameState currentState = new IdleGameState(this); //State of the Game
    List<GameState> registeredStates = new ArrayList<GameState>();
    Iterator stateIterator;

    THashMap<String, GamePlayer> players = new THashMap<String, GamePlayer>();
    THashMap<String, GamePlayer> spectators = new THashMap<String, GamePlayer>();

    public abstract String getName();
    public abstract List<GameState> getAllStates();
    public abstract Location getLobbyLocation();

    public GameState getState(){return currentState;}

    public void setState(GameState state){
        if(!currentState.onStateEnd()) return;
        if(!state.onStateBegin()) return;
        currentState = state;
    }

    public void tick(){
        currentState.tick();
        currentState.incrementRuntime();
    }

    public void nextState(){
        if(stateIterator == null | !stateIterator.hasNext()){
            stateIterator = registeredStates.iterator();
        }
        setState((GameState) stateIterator.next()); //Set state to the next possible state
    }

    public void registerState(GameState state){
        registeredStates.add(state);
    }

    public void addPlayer(Player player){
        players.put(player.getName(), new GamePlayer(player.getName(), this));
        players.get(player.getName()).saveData();
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(getLobbyLocation());
    }

    public void removePlayer(Player player){
        players.get(player.getName()).resetData();
        players.remove(player.getName());
    }

    public void addSpectator(Player player){
        spectators.put(player.getName(), new GamePlayer(player.getName(), this));
        players.get(player.getName()).saveData();
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(getLobbyLocation());
    }

    public void removeSpectator(Player player){
        spectators.get(player.getName()).resetData();
        spectators.remove(player.getName());
    }

}
