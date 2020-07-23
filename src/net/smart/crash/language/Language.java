package net.smart.crash.language;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

public class Language {

	private Map<String, Object> message;
	
	public Language(ConfigurationSection section) {
		this.message = section.getValues(true);
	}
	public String get(String path) {
		return ChatColor.translateAlternateColorCodes('&', (String) message.get(path));
	}
}
