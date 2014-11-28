package me.olivervscreeper.networkutilities.permissions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27/11/2014.
 * Basic permission set that grants
 * permission to those who have the string
 * specified.
 *
 * @author OliverVsCreeper
 */
public class StringPermissionSet extends PermissionSet {

    String permission = "networkutilities.permissions.default";

    public StringPermissionSet(){};
    public StringPermissionSet(String permission){permission = permission;}

    /**
     * Checks to see if the player has the string node
     * provided in Bukkit's Permission API.
     *
     * @param player player to check
     * @return boolean If the player has the string permission.
     */
    @Override
    public boolean playerHasPermission(Player player) {
            if (player.hasPermission(permission)) getCorrectReturn(true);
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
            if(!p.hasPermission(permission)) continue;
            players.add(p.getUniqueId().toString());
        }
        return players;
    }
}
