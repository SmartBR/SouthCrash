package net.smart.crash;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.smart.crash.command.CrashCMD;
import net.smart.crash.language.Language;
import net.smart.crash.reflection.ReflectionManager;

public class Main extends JavaPlugin {

	public static Main getInstance() {
		return getPlugin(Main.class);
	}
	
	public Language language;
	public ReflectionManager reflectionManager;
	
	public void onEnable() {
		saveDefaultConfig();
		language = new Language(getConfig().getConfigurationSection("messages"));
		reflectionManager = new ReflectionManager();
		
		getCommand("crash").setExecutor(new CrashCMD());
		Bukkit.getConsoleSender().sendMessage("§a[SouthCrash] §fPlugin habilitado! §a[v" + getDescription().getVersion() + "]");
	}
}
