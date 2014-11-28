package me.olivervscreeper.networkutilities.permissions;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 26/11/2014.
 * A permission set that holds a list of UUID's.
 * Players can be granted and revoked permission dynamically.
 *
 * @author OliverVsCreeper
 */
public class ListPermissionSet extends PermissionSet {

    private List<String> uniqueUserIdentifiers = new ArrayList<String>();

    public ListPermissionSet(){};
    public ListPermissionSet(List<String> list){this.uniqueUserIdentifiers = list;};

    /**
     * Checks to see if the UUID of the player is contained
     * within the permission set.
     *
     * @param player player to check
     * @return boolean If the UUID of the player is in the permission set.
     */
    @Override
    public boolean playerHasPermission(Player player) {
            if (uniqueUserIdentifiers.contains(player.getUniqueId().toString())) getCorrectReturn(true);
            return getCorrectReturn(false);
    }

    /**
     * Adds the UUID of a player to the list.
     */
    public ListPermissionSet grantPermission(Player p){
        uniqueUserIdentifiers.add(p.getUniqueId().toString());
        return this;
    }

    /**
     * Removes the UUID of a player from the list.
     */
    public ListPermissionSet revokePermission(Player p){
        uniqueUserIdentifiers.remove(p.getUniqueId().toString());
        return this;
    }

    /**
     * Finds all online players applicable to the
     * Permission set.
     *
     * @return List A list of UUID's of applicable players
     */
    @Override
    public List<String> getAllPlayers() {
        return uniqueUserIdentifiers;
    }
}
