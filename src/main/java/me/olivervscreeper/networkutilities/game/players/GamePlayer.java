package me.olivervscreeper.networkutilities.game.players;

import me.olivervscreeper.networkutilities.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class GamePlayer {

    String name;
    Game game;
    PlayerData data;

    public GamePlayer(String name, Game game){
        this.name = name;
        this.game = game;
    }

    public Player getPlayer(){
        return Bukkit.getPlayerExact(name);
    }

    public void saveData(){
        data = new PlayerData().prepare();
    }

    public void resetData(){
        data.revert();
    }

}
