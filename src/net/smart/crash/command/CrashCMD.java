package net.smart.crash.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.smart.crash.Main;
import net.smart.crash.api.CrashAPI;
import net.smart.crash.language.Language;

public class CrashCMD implements CommandExecutor {

	private final Language lang = Main.getInstance().language;
	private final CrashAPI crashAPI = new CrashAPI();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("south.crash")) {
			sender.sendMessage(lang.get("permission-message"));
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(lang.get("args-usage"));
			return true;
		}
		if (args.length >= 1) {
			Player t = Bukkit.getPlayer(args[0]);
			if (t != null) {
				crashAPI.crash(t);
				sender.sendMessage(lang.get("target-crashed").replace("{target}", t.getName()));
			}else {
				sender.sendMessage(lang.get("target-offline"));
			}
		}
		return true;
	}
}
