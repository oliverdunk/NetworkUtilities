package me.olivervscreeper.networkutilities.permissions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27/11/2014.
 * Basic permission set that gives only
 * players with the OP status a granted
 * permission status.
 *
 * @author OliverVsCreeper
 */
public class OPPermissionSet extends PermissionSet {

    /**
     * Checks to see if the given player is an operator.
     *
     * @param player player to check
     * @return boolean If the player is a server operator.
     */
    @Override
    public boolean playerHasPermission(Player player) {
            if (player.isOp()) return getCorrectReturn(true);
            return getCorrectReturn(false);
    }

    /**
     * Finds all online players applicable to the
     * Permission set.
     *
     * @return List A list of UUID's of applicable players
     */
    @Override
    public List<String> getAllPlayers() {
        List<String> players = new ArrayList<String>();
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!p.isOp()) continue;
            players.add(p.getUniqueId().toString());
        }
        return players;
    }

}
