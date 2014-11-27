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
public class FalseListPermissionSet extends PermissionSet {

    private List<String> uniqueUserIdentifiers = new ArrayList<String>();

    public FalseListPermissionSet(){};
    public FalseListPermissionSet(List<String> list){this.uniqueUserIdentifiers = list;};

    /**
     * Checks to see if the UUID of the player is contained
     * within the permission set.
     *
     * @param player player to check
     * @return boolean If the UUID of the player is not in the permission set.
     */
    @Override
    public boolean playerHasPermission(Player player) {
        if(uniqueUserIdentifiers.contains(player.getUniqueId().toString())) return false;
        return true;
    }

    /**
     * Adds the UUID of a player to the list.
     */
    public FalseListPermissionSet grantPermission(Player p){
        uniqueUserIdentifiers.add(p.getUniqueId().toString());
        return this;
    }

    /**
     * Removes the UUID of a player from the list.
     */
    public FalseListPermissionSet revokePermission(Player p){
        uniqueUserIdentifiers.remove(p.getUniqueId().toString());
        return this;
    }
}
