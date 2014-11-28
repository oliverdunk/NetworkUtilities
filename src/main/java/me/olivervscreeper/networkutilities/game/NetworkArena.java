package me.olivervscreeper.networkutilities.game;

import me.olivervscreeper.networkutilities.game.events.ArenaEndEvent;
import me.olivervscreeper.networkutilities.game.events.ArenaResetEvent;
import me.olivervscreeper.networkutilities.game.events.ArenaStartEvent;
import me.olivervscreeper.networkutilities.game.events.ArenaTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
    private int savedMaxRuntime;
    private int savedLobbyRuntime;

    private int maxRuntime;
    private int lobbyRuntime;
    private NetworkArenaState state = NetworkArenaState.LOBBY;

    private List<String> playerUUIDs = new ArrayList<String>();

    public NetworkArena(String mapName, String mapAuthor, List<Location> spawns, int maxPlayers, int maxRuntime, int lobbyRuntime){
        this.mapName = mapName;
        this.mapAuthor = mapAuthor;
        this.spawns = spawns;
        this.maxPlayers = maxPlayers;

        this.maxRuntime = maxRuntime;
        this.lobbyRuntime = lobbyRuntime;

        this.savedMaxRuntime = maxRuntime;
        this.savedLobbyRuntime = lobbyRuntime;
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

    @EventHandler(priority = EventPriority.LOWEST)
    public void onArenaTick(ArenaTickEvent event){
        if(!event.getArena().equals(this)) return;
        switch(state){
            case LOBBY:
                lobbyRuntime--;
                if(lobbyRuntime != 0) return;
                ArenaStartEvent startEvent = new ArenaStartEvent(this);
                Bukkit.getServer().getPluginManager().callEvent(startEvent);
                state = NetworkArenaState.IN_GAME;
                break;
            case IN_GAME:
                maxRuntime--;
                if(lobbyRuntime != 0) return;
                ArenaEndEvent endEvent = new ArenaEndEvent(this);
                Bukkit.getServer().getPluginManager().callEvent(endEvent);
                state = NetworkArenaState.RESETTING;
                break;
            case RESETTING:
                ArenaResetEvent resetEvent = new ArenaResetEvent(this);
                Bukkit.getServer().getPluginManager().callEvent(resetEvent);
                maxRuntime = savedMaxRuntime;
                lobbyRuntime = savedLobbyRuntime;
                playerUUIDs.clear();
                state = NetworkArenaState.LOBBY;
                break;
            case MAINTENANCE:
                break;
        }
    }
}
