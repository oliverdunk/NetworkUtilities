package me.olivervscreeper.networkutilities.game.states;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public abstract class ExtensibleGameState {

    int runtime = 0;

    public int getRuntime() {return runtime;}
    public void incrementRuntime(){runtime++;}

    public abstract String getName();
    public abstract String getDisplayName();

    public abstract Boolean onStateBegin();
    public abstract Boolean onStateEnd();

    public abstract void tick();

}
