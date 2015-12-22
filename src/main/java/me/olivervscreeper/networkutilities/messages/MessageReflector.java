package me.olivervscreeper.networkutilities.messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Reflects titles and subtitles, version independent. Heavily adapted before use.
 *
 * @author Maxim Van de Wynckel
 * @version 1.1.0
 */
public class MessageReflector {

    private static final Map<Class<?>, Class<?>> CORRESPONDING_TYPES = new HashMap<Class<?>, Class<?>>();
    /* Title packet */
    private Class<?> packetTitle;
    private Class<?> packetChat;
    /* Title packet actions ENUM */
    private Class<?> packetActions;
    /* Chat serializer */
    private Class<?> nmsChatSerializer;
    private Class<?> chatBaseComponent;

    public MessageReflector() {
        loadClasses();
    }

    private static boolean equalsTypeArray(Class<?>[] a, Class<?>[] o) {
        if (a.length != o.length)
            return false;
        for (int i = 0; i < a.length; i++)
            if (!a[i].equals(o[i]) && !a[i].isAssignableFrom(o[i]))
                return false;
        return true;
    }

    private void loadClasses() {
        packetTitle = getNMSClass("PacketPlayOutTitle");
        packetChat = getNMSClass("PacketPlayOutChat");
        packetActions = getNMSClass("PacketPlayOutTitle$EnumTitleAction");
        chatBaseComponent = getNMSClass("IChatBaseComponent");
        nmsChatSerializer = getNMSClass("IChatBaseComponent$ChatSerializer");
    }

    public void send(int type, Player player, String title, int in, int stay, int out) {
        try {
            Object handle = getHandle(player);
            Object connection = getField(handle.getClass(), "playerConnection").get(handle);
            Object[] actions = packetActions.getEnumConstants();

            Object packet = packetTitle.getConstructor(packetActions, chatBaseComponent, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(actions[2], null, in, stay, out);
            Method sendPacket = getMethod(connection.getClass(), "sendPacket");
            sendPacket.invoke(connection, packet);

            Object serialized = getMethod(nmsChatSerializer, "a", String.class).invoke(null, "{text:\"" + ChatColor.translateAlternateColorCodes('&', title) + "\"" + "}");
            packet = packetTitle.getConstructor(packetActions, chatBaseComponent).newInstance(actions[type], serialized);
            sendPacket.invoke(connection, packet);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void sendActionbar(Player player, String title, int in, int stay, int out) {
        try {
            Object handle = getHandle(player);
            Object connection = getField(handle.getClass(), "playerConnection").get(handle);
            Method sendPacket = getMethod(connection.getClass(), "sendPacket");
            Object[] actions = packetActions.getEnumConstants();

            Object packet = packetTitle.getConstructor(packetActions, chatBaseComponent, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(actions[2], null, in, stay, out);
            //sendPacket.invoke(connection, packet);

            Object serialized = getMethod(nmsChatSerializer, "a", String.class).invoke(null, "{text:\"" + ChatColor.translateAlternateColorCodes('&', title) + "\"" + "}");
            packet = packetChat.getConstructor(chatBaseComponent, byte.class).newInstance(serialized, (byte) 2);
            sendPacket.invoke(connection, packet);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Class<?> getPrimitiveType(Class<?> clazz) {
        return CORRESPONDING_TYPES.containsKey(clazz) ? CORRESPONDING_TYPES
                .get(clazz) : clazz;
    }

    private Class<?>[] toPrimitiveTypeArray(Class<?>[] classes) {
        int a = classes != null ? classes.length : 0;
        Class<?>[] types = new Class<?>[a];
        for (int i = 0; i < a; i++)
            types[i] = getPrimitiveType(classes[i]);
        return types;
    }

    private Object getHandle(Object obj) {
        try {
            return getMethod("getHandle", obj.getClass()).invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Method getMethod(String name, Class<?> clazz,
                             Class<?>... paramTypes) {
        Class<?>[] t = toPrimitiveTypeArray(paramTypes);
        for (Method m : clazz.getMethods()) {
            Class<?>[] types = toPrimitiveTypeArray(m.getParameterTypes());
            if (m.getName().equals(name) && equalsTypeArray(types, t))
                return m;
        }
        return null;
    }

    private String getVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        String version = name.substring(name.lastIndexOf('.') + 1) + ".";
        return version;
    }

    private Class<?> getNMSClass(String className) {
        String fullName = "net.minecraft.server." + getVersion() + className;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(fullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private Field getField(Class<?> clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Method getMethod(Class<?> clazz, String name, Class<?>... args) {
        for (Method m : clazz.getMethods())
            if (m.getName().equals(name)
                    && (args.length == 0 || ClassListEqual(args,
                    m.getParameterTypes()))) {
                m.setAccessible(true);
                return m;
            }
        return null;
    }

    private boolean ClassListEqual(Class<?>[] classListOne, Class<?>[] classListTwo) {
        boolean equal = true;
        if (classListOne.length != classListTwo.length)
            return false;
        for (int i = 0; i < classListOne.length; i++)
            if (classListOne[i] != classListTwo[i]) {
                equal = false;
                break;
            }
        return equal;
    }
}