package me.olivervscreeper.networkutilities.permissions;

import org.bukkit.entity.Player;

/**
 * Created on 27/11/2014.
 * Basic permission set that grants
 * permission to those who have the string
 * specified.
 *
 * @author OliverVsCreeper
 */
public class stringPermissionSet extends permissionSet {

    String permission = "networkutilities.permission.default";

    public stringPermissionSet(){};
    public stringPermissionSet(String permission){permission = permission;}

    /**
     * Checks to see if the player has the string node
     * provided in Bukkit's Permission API.
     *
     * @return boolean If the player has the string permission.
     */
    @Override
    public boolean playerHasPermission(Player p) {
        if(p.hasPermission(permission)) return true;
        return false;
    }
}
