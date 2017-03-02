package me.reparo.killcommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.reparo.killcommands.utilities.Util;

public class SetUpNewKillConfig implements CommandExecutor {
	
	 
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("SetUpNewKillConfig")) {
			if(args.length == 1 && s.hasPermission("killcommands.setupnewkillconfig")) {
				Util.util.getKillCommands().set(args[0] + ".Required permissions", "requiredpermission");
				Util.util.getKillCommands().set(args[0] + ".Command executioner", "killer");
				Util.util.getKillCommands().set(args[0] + ".Commands to execute", Util.defaultCommands());
				Util.util.saveKillCommands();
			}
		}
		
		
		
		return true;
	}
	
}