package me.olivervscreeper.networkutilities.game;

import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.game.states.IdleGameState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 29/11/2014.
 *
 * @author OliverVsCreeper
 */
public abstract class Game {

    //TODO: Allow players to join, and create events for Game Systems
    //TODO: Add system for cycling to the next state
    //TODO: Add basic system for Arenas
    //TODO: Add a configuration system for loading and saving game information
    //e.g Authors, Description, Help

    GameState currentState = new IdleGameState(this); //State of the Game
    List<GamePlayer> players = new ArrayList<GamePlayer>();
    List<GamePlayer> spectators = new ArrayList<GamePlayer>();

    public abstract String getName();
    public abstract List<GameState> getAllStates();

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

}
