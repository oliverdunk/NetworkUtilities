package me.olivervscreeper.networkutilities.game.players;

import me.olivervscreeper.networkutilities.game.NetworkGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created on 30/11/2014.
 *
 * @author OliverVsCreeper
 */
public class GamePlayer {

    String name;
    NetworkGame game;
    PlayerData data;

    public GamePlayer(String name, NetworkGame game){
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
