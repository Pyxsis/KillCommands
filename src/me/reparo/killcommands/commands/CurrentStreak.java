package me.reparo.killcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.reparo.killcommands.utilities.Util;

public class CurrentStreak implements CommandExecutor {
	

	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("currentstreak")) {
			if(!(s instanceof Player)) {
				s.sendMessage("You must be a player!"); 
				return true; 
			}
			
			Player killer = (Player) s;
			if(s.hasPermission("killcommands.killstreak.other")) {
				if(args.length >= 1) {
					if(Util.killtracker.containsKey(args[0])) {
						if(Bukkit.getPlayer(args[0]) != null) {
							s.sendMessage(Util.currentStreak(Bukkit.getPlayer(args[0])));	
						} else {
							s.sendMessage(ChatColor.RED + "An error has occured");
						}
					} else {
						s.sendMessage(ChatColor.RED + "The target doesn't have a streak!");
					}
				} else {
					s.sendMessage(Util.currentStreak(killer));
				}
			} else if (s.hasPermission("killcommands.killstreak")) {
				s.sendMessage(Util.currentStreak(killer));
			} else {
				s.sendMessage(Util.colour("&4Lacking permissions..."));
			}
		}
		
		
		
		return true;
	}
	
}