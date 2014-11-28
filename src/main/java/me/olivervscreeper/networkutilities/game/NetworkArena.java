package me.olivervscreeper.networkutilities.game;

import me.olivervscreeper.networkutilities.utils.MathUtils;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/11/2014.
 *
 * @author OliverVsCreeper
 */
public class NetworkArena {

    private String mapName;
    private String mapAuthor;
    private List<Location> spawns;

    private int maxPlayers = 8;
    private int maxRuntime = MathUtils.secondsToTick(900);
    private NetworkArenaState state = NetworkArenaState.LOBBY;

    private List<String> playerUUIDs = new ArrayList<String>();

    public NetworkArena(String mapName, String mapAuthor, List<Location> spawns){
        this.mapName = mapName;
        this.mapAuthor = mapAuthor;
        this.spawns = spawns;
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
}
