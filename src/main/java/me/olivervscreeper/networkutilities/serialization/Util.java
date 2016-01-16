package me.olivervscreeper.networkutilities.serialization;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Util {

    private static final String numberRegex = "\\d+";

    protected Util() {
    }

    /**
     * Method used to test whether a string is an Integer or not
     *
     * @param s The string to test
     * @return Whether the given string is an Integer
     */
    public static boolean isNum(String s) {
        return s.matches(numberRegex);
    }

    /**
     * Test
     *
     * @return True if the given material is Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE,
     * Material.LEATHER_LEGGINGS, or  Material.LEATHER_BOOTS;
     */
    public static boolean isLeatherArmor(Material material) {
        return material == Material.LEATHER_HELMET || material == Material.LEATHER_CHESTPLATE ||
                material == Material.LEATHER_LEGGINGS || material == Material.LEATHER_BOOTS;
    }

    public static boolean isLeatherArmor(ItemStack itemStack) {
        return itemStack.getItemMeta() instanceof LeatherArmorMeta;
    }

    public static boolean keyFound(String[] array, String key) {
        for (String s : array) {
            if (s.equalsIgnoreCase(key)) {
                ;
            }
        }
        return false;
    }

}
