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
public class listPermissionSet extends permissionSet {

    private List<String> uniqueUserIdentifiers = new ArrayList<String>();

    public listPermissionSet(){};
    public listPermissionSet(List<String> list){this.uniqueUserIdentifiers = list;};

    /**
     * Checks to see if the UUID of the player is contained
     * within the permission set.
     *
     * @return boolean If the UUID of the player is in the permission set.
     */
    @Override
    public boolean playerHasPermission(Player p) {
        if(uniqueUserIdentifiers.contains(p.getUniqueId().toString())) return true;
        return false;
    }

    /**
     * Adds the UUID of a player to the list.
     */
    public void grantPermission(Player p){
        uniqueUserIdentifiers.add(p.getUniqueId().toString());
    }

    /**
     * Removes the UUID of a player from the list.
     */
    public void revokePermission(Player p){
        uniqueUserIdentifiers.remove(p.getUniqueId().toString());
    }
}
