package me.reparo.killcommands.utilities;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ExecutingCommands {
	
	static List<String> commandsToExecute(String killamount) {
		if (Util.util.getKillCommands().getStringList(killamount + ".Commands to execute") == null) {
			return null;
		} else {
			return Util.util.getKillCommands().getStringList(killamount + ".Commands to execute");
		} 
		
	}

	static String permissionRequired(String killamount) {
		if(Util.util.getKillCommands().getString(killamount + ".Required permission") == null) {
			return "";
		} else {
			return Util.util.getKillCommands().getString(killamount) + ".Required permission";
		}
	}
	
	static CommandSender whoExecutesCommands(Player killer, Entity dead, String killamount) {
		if(Util.util.getKillCommands().getString(killamount + ".Command executioner").equalsIgnoreCase("killer")) {
			return killer;
		} else if(Util.util.getKillCommands().getString(killamount + ".Command executioner").equalsIgnoreCase("dead")) {
			return dead;
		} else {
			return Bukkit.getConsoleSender();
		}
	}
	
	public static void executeCommands(Player killer, Entity dead, String killamount) {
		if(commandsToExecute(killamount) == null) { return; }
		if(killer.hasPermission(permissionRequired(killamount))) {
			List<String> cte = commandsToExecute(killamount);
			for(String command : cte) {
				if(dead instanceof Player) { command = command.replace("%deadplayername", dead.getName()).replace("%deadplayerdisplayname", ((Player)dead).getDisplayName()); }
				command = command.replace("%killername", killer.getName()).replace("%deadentityname", dead.getName()).replace("%currentkillamount", killamount).replace("%killerhealth", killer.getHealth() + "").replace("%killerlocation", killer.getLocation().toString());
				Bukkit.getServer().dispatchCommand(whoExecutesCommands(killer, dead, killamount), command);
			}
		}
	}
}
