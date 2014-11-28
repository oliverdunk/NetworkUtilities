package me.olivervscreeper.networkutilities.serialization;

import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import me.olivervscreeper.networkutilities.serialization.json.JSONException;
import me.olivervscreeper.networkutilities.serialization.json.JSONObject;

/**
 * A class to help with the serialization of LivingEntities. If the given
 * LivingEntity is a Player, it is cast as such and redirected to
 * PlayerSerialization. <br/>
 * <br/>
 * This serialization class supports optional serialization.<br/>
 * TacoSerialization will create a folder in your server plugins directory
 * (wherever that may be) called 'TacoSerialization'. Inside the folder will be
 * a config.yml file. Various values can be turned off to prevent some keys from
 * being generated.
 * 
 * @author KILL3RTACO
 * @since TacoSerialization 1.1
 *
 */
public class LivingEntitySerialization {
	
	protected LivingEntitySerialization() {}
	
	/**
	 * Serialize a LivingEntity into a JSONObject.
	 * 
	 * @param entity
	 *            the entity to serialize
	 * @return the serialized entity, in the from of a JSONObject
	 */
	@SuppressWarnings("deprecation")
	public static JSONObject serializeEntity(LivingEntity entity) {
//		if (entity instanceof Player) {
//			return PlayerSerialization.serializePlayer((Player) entity);
//		}
		try {
			JSONObject root = new JSONObject();
			root.put("age", ((Ageable) entity).getAge());
			root.put("health", entity.getHealth());
			root.put("name", entity.getCustomName());
			root.put("potion-effects", PotionEffectSerialization.serializeEffects(entity.getActivePotionEffects()));
			root.put("type", entity.getType().getTypeId());
			return root;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Serialize a LivingEntity into a String.
	 * 
	 * @param entity
	 *            The LivingEntity to serialize
	 * @return The serialization string
	 */
	public static String serializeEntityAsString(LivingEntity entity) {
		return serializeEntityAsString(entity, false);
	}
	
	/**
	 * Serialize a LivingEntity into a String.
	 * 
	 * @param entity
	 *            The LivingEntity to serialize
	 * @param pretty
	 *            Whether the resulting string should be 'pretty' or not
	 * @return The serialization string
	 */
	public static String serializeEntityAsString(LivingEntity entity, boolean pretty) {
		return serializeEntityAsString(entity, pretty, 5);
	}
	
	/**
	 * Serialize a LivingEntity into a String.
	 * 
	 * @param entity
	 *            The LivingEntity to serialize
	 * @param pretty
	 *            Whether the resulting string should be 'pretty' or not
	 * @param indentFactor
	 *            The amount of spaces in a tab
	 * @return The serialization string
	 */
	public static String serializeEntityAsString(LivingEntity entity, boolean pretty, int indentFactor) {
		try {
			if (pretty) {
				return serializeEntity(entity).toString(indentFactor);
			} else {
				return serializeEntity(entity).toString();
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Spawn a LivingEntity in a desired Location with the given stats. The
	 * given string will be constructed into a JSONObject to use a reference to
	 * the entities stats.
	 * 
	 * @param location
	 *            Where the entity should be spawned
	 * @param stats
	 *            The stats of the entity
	 * @return The LivingEntity spawned
	 */
	public static LivingEntity spawnEntity(Location location, String stats) {
		try {
			return spawnEntity(location, new JSONObject(stats));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Spawn a LivingEntity in a desired Location with the given stats.
	 * 
	 * @param location
	 *            Where the entity should be spawned
	 * @param stats
	 *            The stats of the entity
	 * @return The LivingEntity spawned
	 */
	public static LivingEntity spawnEntity(Location location, JSONObject stats) {
		try {
			if (!stats.has("type")) {
				throw new IllegalArgumentException("The type of the entity cannot be determined");
			} else {
				LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.fromId(stats.getInt("type")));
				if (stats.has("age") && entity instanceof Ageable)
					((Ageable) entity).setAge(stats.getInt("age"));
				if (stats.has("health"))
					entity.setHealth(stats.getDouble("health"));
				if (stats.has("name"))
					entity.setCustomName(stats.getString("name"));
				if (stats.has("potion-effects"))
					;
				PotionEffectSerialization.addPotionEffects(stats.getString("potion-effects"), entity);
				return entity;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
