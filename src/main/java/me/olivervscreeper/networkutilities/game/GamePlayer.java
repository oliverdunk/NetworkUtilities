package me.olivervscreeper.networkutilities.game;

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

    public GamePlayer(String name, Game game){
        this.name = name;
        this.game = game;
    }

    public Player getPlayer(){
        return Bukkit.getPlayerExact(name);
    }

    public void prepare(){
        //TODO: Save inventory, location and health and set to adventure mode with no data
    }

    public void revert(){
        //TODO: Revert the player using data from the prepare() method
    }

}
