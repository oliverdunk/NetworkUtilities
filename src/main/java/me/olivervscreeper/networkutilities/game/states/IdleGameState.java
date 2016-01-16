package me.olivervscreeper.networkutilities.game.states;

import me.olivervscreeper.networkutilities.game.Game;

/**
 * @author OliverVsCreeper
 */
public class IdleGameState extends GameState {

    public IdleGameState(Game game) {
        super(game, "idle", "Idle");
    }

    @Override
    public boolean onStateBegin() {
        registerListener(this);
        return true;
    }

    @Override
    public boolean onStateEnd() {
        unregisterListener(this);
        return true;
    }

    @Override
    public void tick() {
        return;
    }

}
