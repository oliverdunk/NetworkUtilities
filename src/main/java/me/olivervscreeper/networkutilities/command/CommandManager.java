package me.olivervscreeper.networkutilities.command;

import me.olivervscreeper.networkutilities.utils.ListUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/11/2014.
 * Template CommandManager.
 * Handles the loading and monitoring
 * of commands and executes methods with permission
 * checks where neccessary.
 *
 * @author OliverVsCreeper
 */
public class CommandManager implements Listener{

    List<Method> commands = new ArrayList<Method>();
    public String permissionMessage = ChatColor.RED + "It seems that you can't do this right now!";
    public String errorMessage = ChatColor.RED + "BOOM! It seems that command didn't work.";

    public CommandManager(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Loops through a class and registers
     * all correctly setup commands
     * into the CommandManager.
     *
     * @param commandClass the class to check
     */
    public void registerCommands(Class commandClass){
        for(Method command : commandClass.getMethods()) {
            if (command.getAnnotation(Command.class) == null) continue;
            try {
                commands.add(command); //Register command
            }catch(Exception ex){
                ex.printStackTrace();
                continue;
            }
        }
    }

    @EventHandler
    public void onCommandPre(PlayerCommandPreprocessEvent event){
        List<String> messageArgs = ListUtils.splitString(event.getMessage(), " ");
        String command = messageArgs.iterator().next();
        messageArgs.remove(command);
        Boolean success = parseCommand(event.getPlayer(), command.replace("/", ""), messageArgs);
        if(success) event.setCancelled(true);
    }

    /**
     * Checks an executed command against all
     * stored commands. If the command is
     * registered, it will be executed.
     *
     * @param player player running command (for permissions)
     * @param command name of the command being run
     * @param args arguments used by the executor
     * @return boolean If a command was executed.
     */
    public Boolean parseCommand(Player player, String command, List<String> args){
         for(Method method : commands) {
            String commandName = method.getAnnotation(Command.class).command();
            String permission = method.getAnnotation(Command.class).permission();
            if(!commandName.equals(command)) continue;
                if(!player.hasPermission(permission) && !permission.equals("none")){
                    player.sendMessage(permissionMessage);
                    return true;
                }
             try {
                 method.invoke(null, player, args);
             }catch(Exception ex){
                 player.sendMessage(errorMessage);
                 ex.printStackTrace();
             }
            return true;
        }
        return false;
    }

}
