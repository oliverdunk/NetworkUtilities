package me.olivervscreeper.networkutilities.game.extensions;

import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.events.PlayerJoinGameEvent;
import me.olivervscreeper.networkutilities.game.events.PlayerLeaveGameEvent;
import me.olivervscreeper.networkutilities.game.players.GamePlayer;
import me.olivervscreeper.networkutilities.messages.Message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 06/12/2014.
 */
public class TeamsExtension extends GameExtension implements Listener {

  List<Team> teams = new ArrayList<Team>();
  Scoreboard customScoreboard;
  boolean enabled = false;

  Iterator teamIterator;
  List<Team> fillFirst = new ArrayList<Team>();

  public TeamsExtension(Game gameInstance) {
    super(gameInstance);
    createScoreboard();
  }

  public void createScoreboard() {
    customScoreboard =
        Bukkit.getScoreboardManager().getNewScoreboard();
  }

  public Scoreboard getScoreboard() {
    return customScoreboard;
  }

  @Override //To prevent players without a team, loading in game is disabled
  public boolean onEnable() {
    if (gameInstance.players.size() > 0) {
      gameInstance.getLogger().log("Teams",
                                   "Extension cannot be enabled with players ingame");
      return false;
    }
    if (enabled) {
      gameInstance.getLogger().log("Teams", "Extension already enabled");
      return false;
    }
    registerListener(this);
    teamIterator = teams.iterator();
    enabled = true;
    gameInstance.getLogger().log("Teams", "Team extension enabled");
    return true;
  }

  @Override
  public String getName() {
    return "Team Extension";
  }

  public void registerTeam(String name, String display, String prefix, String suffix) {
    if (enabled) {
      return;
    }
    Team team = customScoreboard.registerNewTeam(name);
    team.setDisplayName(display);
    team.setPrefix(prefix);
    team.setSuffix(suffix);
    teams.add(team);
    gameInstance.getLogger().log("Teams", name + " team has been registered into the system");
  }

  public Team getTeam(Player player) {
    return customScoreboard.getPlayerTeam(player);
  }

  public Team getTeam(GamePlayer player) {
    return customScoreboard.getPlayerTeam(player.getPlayer());
  }

  @EventHandler
  public void onJoin(PlayerJoinGameEvent event) {
    if (!teamIterator.hasNext()) {
      teamIterator = teams.iterator();
    }
    Team team;
    if (fillFirst.size() > 0) {
      team = fillFirst.get(0);
      fillFirst.remove(0);
    } else {
      team = (Team) teamIterator.next();
    }
    team.addPlayer(event.getPlayer());
    new Message(Message.INFO).addRecipient(event.getPlayer())
        .send("You were added to the " + team.getDisplayName() + ChatColor.GRAY + " team!");
    gameInstance.getLogger()
        .log("Teams", "Player " + event.getPlayer().getName() + " was added to " + team.getName());
  }

  @EventHandler
  public void onLeave(PlayerLeaveGameEvent event) {
    fillFirst.add(getTeam(event.getPlayer()));
    getTeam(event.getPlayer()).removePlayer(event.getPlayer());
    new Message(Message.INFO).addRecipient(event.getPlayer())
        .send("You were removed from your team.");
    gameInstance.getLogger()
        .log("Teams", "Player " + event.getPlayer().getName() + " was removed from their team");
  }

}
