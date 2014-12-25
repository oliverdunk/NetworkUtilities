package me.olivervscreeper.networkutilities.game.states;

import me.olivervscreeper.networkutilities.game.Game;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class IdleGameState extends GameState {

  public IdleGameState(Game game) {
    super(game);
  }

  @Override
  public String getName() {
    return "idle";
  }

  @Override
  public String getDisplayName() {
    return "Idle";
  }

  @Override
  public Boolean onStateBegin() {
    registerListener(this);
    return true;
  }

  @Override
  public Boolean onStateEnd() {
    unregisterListener(this);
    return true;
  }

  @Override
  public void tick() {
    return;
  }
}
