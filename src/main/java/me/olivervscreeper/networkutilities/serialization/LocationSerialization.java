package me.olivervscreeper.networkutilities.serialization;

import me.olivervscreeper.networkutilities.serialization.json.JSONException;
import me.olivervscreeper.networkutilities.serialization.json.JSONObject;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

/**
 * A class to help with the serialization of locations.
 *
 * @author KILL3RTACO
 * @author OliverVsCreeper
 */
public class LocationSerialization {

  protected LocationSerialization() {
  }

  /**
   * Serialize Location, saving all co-ordinates.
   *
   * @param loc The Location to serialize
   * @return The serialized location information
   */
  public static JSONObject serializeLocation(Location loc) {
    try {
      JSONObject root = new JSONObject();
      root.put("x", loc.getX());
      root.put("y", loc.getY());
      root.put("z", loc.getZ());
      root.put("pitch", loc.getPitch());
      root.put("yaw", loc.getYaw());
      root.put("world", loc.getWorld().getName());
      return root;
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Serializes the Location and returns the String form.
   *
   * @param loc The Location to serialized
   * @return The serialization string
   */
  public static String serializeLocationAsString(Location loc) {
    return serializeLocationAsString(loc, false);
  }

  /**
   * Serializes the Location and returns the String form.
   *
   * @param loc    The Location to serialized
   * @param pretty Whether the resulting String should be 'pretty' or not
   * @return The serialization string
   */
  public static String serializeLocationAsString(Location loc, boolean pretty) {
    return serializeLocationAsString(loc, pretty, 5);
  }

  /**
   * Serializes the Location and returns the String form.
   *
   * @param loc          The Location to serialized
   * @param pretty       Whether the resulting String should be 'pretty' or not
   * @param indentFactor the amount of spaces in a tab
   * @return The serialization string
   */
  public static String serializeLocationAsString(Location loc, boolean pretty, int indentFactor) {
    try {
      if (pretty) {
        return serializeLocation(loc).toString(indentFactor);
      } else {
        return serializeLocation(loc).toString();
      }
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Gets Location from the a JSONObject constructed from the given String
   *
   * @param json The String to use
   * @return Location taken from a JSONObject constructed from the given String
   */
  public static Location getLocationMeta(String json) {
    try {
      return getLocationMeta(new JSONObject(json));
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Gets Location from the a JSONObject constructed from the given JSONObject
   *
   * @param json The JSONObject to decode
   * @return Location taken from the given JSONObject as a reference
   */
  public static Location getLocationMeta(JSONObject json) {
    try {
      World w = Bukkit.createWorld(new WorldCreator(json.getString("world")));
      return new Location(w, json.getDouble("x"), json.getDouble("y"), json.getDouble("z"),
                          Float.parseFloat(json.getDouble("yaw") + ""),
                          Float.parseFloat(json.getDouble("pitch") + ""));
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }

}
