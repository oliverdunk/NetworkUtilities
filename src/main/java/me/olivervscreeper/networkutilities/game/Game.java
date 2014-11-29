package me.olivervscreeper.networkutilities.game;

/**
 * Created on 29/11/2014.
 *
 * @author OliverVsCreeper
 */
public abstract class Game {

    GameState currentState = GameState.IDLE; //State of the Game
    public abstract void tick();  //Tick method, called whenever a NetworkGame tick occurs

    public GameState getState(){return currentState;}
    public void setState(GameState state){currentState = state;}

}
