package me.olivervscreeper.networkutilities.permissions;

import org.bukkit.entity.Player;

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
}
