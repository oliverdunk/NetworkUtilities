package me.olivervscreeper.networkutilities.game;

import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.game.events.ArenaTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/11/2014.
 *
 * @author OliverVsCreeper
 */
public class NetworkArenaManager {

    public List<NetworkArena> arenas = new ArrayList<NetworkArena>();

    public List<NetworkArena> getAllArenas(){
        return arenas;
    }

    public NetworkArena getArenaByPlayer(Player player){
        String UUID = player.getUniqueId().toString();
        for(NetworkArena arena : arenas){
            for(String currentUUID : arena.getAllPlayers()){
                if(currentUUID.equals(UUID)) return arena;
            }
        }
        return null;
    }

    public void tick(){
        for(NetworkArena a : arenas){
            ArenaTickEvent event = new ArenaTickEvent(a);
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    public void addArena(NetworkArena arena){
        arenas.add(arena);
        Bukkit.getPluginManager().registerEvents(arena, NetworkUtilities.plugin);
    }

}
