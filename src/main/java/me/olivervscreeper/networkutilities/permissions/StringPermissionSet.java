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
public class StringPermissionSet extends PermissionSet {

    String permission = "networkutilities.permission.default";

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
}
