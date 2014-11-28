package me.olivervscreeper.networkutilities.game;

import me.olivervscreeper.networkutilities.game.events.ArenaTickEvent;
import me.olivervscreeper.networkutilities.utils.MathUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/11/2014.
 *
 * @author OliverVsCreeper
 */
public class NetworkArena implements Listener{

    private String mapName;
    private String mapAuthor;
    private List<Location> spawns;
    private int maxPlayers;

    private int maxRuntime = MathUtils.secondsToTick(900);
    private int lobbyRuntime = MathUtils.secondsToTick(60);
    private NetworkArenaState state = NetworkArenaState.LOBBY;

    private List<String> playerUUIDs = new ArrayList<String>();

    public NetworkArena(String mapName, String mapAuthor, List<Location> spawns, int maxPlayers){
        this.mapName = mapName;
        this.mapAuthor = mapAuthor;
        this.spawns = spawns;
        this.maxPlayers = maxPlayers;
    }

    public List<String> getAllPlayers(){
        return playerUUIDs;
    }

    public NetworkArenaState getState() {
        return state;
    }

    public void setState(NetworkArenaState state) {
        this.state = state;
    }

    @EventHandler
    public void onArenaTick(ArenaTickEvent event){
        if(!event.getArena().equals(this)) return;
    }
}
