package net.smart.crash.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReflectionManager {

	private final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	private Method getHandle;
	private Method sendPacket;
	private Field playerConnectionField;
	
	public ReflectionManager() {
		try {
			getHandle = getOBClass("entity.CraftPlayer").getMethod("getHandle");
			sendPacket = getNMSClass("PlayerConnection").getMethod("sendPacket", getNMSClass("Packet"));
			playerConnectionField = getNMSClass("EntityPlayer").getField("playerConnection");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Class<?> getNMSClass(String name) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName("net.minecraft.server." + version + "." + name);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}
	public Class<?> getOBClass(String name) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName("org.bukkit.craftbukkit." + version + "." + name);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}
	public void sendPacket(Player player, Object packet) {
		try {
			Object entityPlayer = getHandle.invoke(player);
			Object playerConnection = playerConnectionField.get(entityPlayer);
			sendPacket.invoke(playerConnection, packet);
		}catch (Exception e) {}
	}
}
