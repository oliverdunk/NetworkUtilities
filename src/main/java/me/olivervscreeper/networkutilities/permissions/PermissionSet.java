package me.olivervscreeper.networkutilities.permissions;

import org.bukkit.entity.Player;

/**
 * Created on 26/11/2014.
 * Default abstract class which contains the layout for
 * the general permission set system.
 *
 * @author OliverVsCreeper
 */
public abstract class PermissionSet {

    public boolean inverted = false;

    public abstract boolean playerHasPermission(Player p);

    public void invert(){inverted=true;}

    public boolean getCorrectReturn(boolean value){
        if(!inverted) return value;
        return !value;
    }

}
