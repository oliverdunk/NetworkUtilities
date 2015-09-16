package me.olivervscreeper.networkutilities.command;

import junit.framework.TestCase;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OliverVsCreeper
 */
public class CommandManagerTest extends TestCase {

    public boolean hasRun = false;

    public void testParseCommand(){
        System.out.println("* CommandManagerTest: testParseCommand()");

        Plugin plugin = mock(Plugin.class);
        PluginManager pManager = mock(PluginManager.class);

        Server server = mock(Server.class);
        when(server.getPluginManager()).thenReturn(pManager);

        when(plugin.getServer()).thenReturn(server);

        CommandManager manager = new CommandManager(plugin);
        manager.registerCommands(this, false);

        Player player = mock(Player.class);
        when(player.getName()).thenReturn("olivervscreeper");
        manager.parseCommand(player, "test", new ArrayList<String>());

        assertTrue(hasRun);
    }

    @Command(label = "test")
    public void onTestCommand(Player player, List<String> args){
        hasRun = true;
    }

}