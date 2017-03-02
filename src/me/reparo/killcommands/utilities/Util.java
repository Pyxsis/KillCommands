package me.reparo.killcommands.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.reparo.killcommands.KillCommands;

public class Util {
	
	public static String colour(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static Util util = new Util();
	
	/*
	 * Tracking player's kills
	 */
	public static HashMap<String, Integer> killtracker = new HashMap<String, Integer>();
	/*
	 * Default commands for SetUpNewKillConfig
	 */
	public static List<String> defaultCommands() {
		List<String> def = new ArrayList<String>();
		def.clear();
		def.add("broadcast congratulations %killername on %currentkillamount!");
		def.add("eco give %killername %currentkillamount");
		return def;
	}
	/*
	 * Current streak 
	 */
	public static String currentStreak(Player killer) {
		if(killtracker.get(killer.getName()) != null) {
			return colour(KillCommands.kc.getConfig().getString("Current streak format")
					.replace("%killername", killer.getName()).replace("%currentkillamount", killtracker.get(killer.getName()).toString()).replace("%killerhealth", killer.getHealth() + "").replace("%killerlocation", killer.getLocation().toString()));	
		} else {
			return colour(KillCommands.kc.getConfig().getString("Current streak format")
					.replace("%killername", killer.getName()).replace("%currentkillamount", "0").replace("%killerhealth", killer.getHealth() + "").replace("%killerlocation", killer.getLocation().toString()));
		}
	}
	
	/*
	 * Clear streak on log out?
	 */
	public static boolean clearStreakOnLogout() {
		return KillCommands.kc.getConfig().getBoolean("Should streaks be cleared when the killer logs out");
	}
	
	/*
	 * Custom file handling
	 */
    FileConfiguration ks;
    File ksfile;
   
    public void setupKillCommandsYAML() {
    	ksfile = new File(KillCommands.kc.getDataFolder(), "killcommands.yml");
    	if (!ksfile.exists()) {
    		try {
    			ksfile.createNewFile();
    		} catch (IOException e) {
    			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create killcommands.yml!");
    			}
    	}           
    	ks = YamlConfiguration.loadConfiguration(ksfile);
    }
    public FileConfiguration getKillCommands() {
            return ks;
    }
    public void saveKillCommands() {
    	try { ks.save(ksfile); } 
    	catch (IOException e) { Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save killcommands.yml!"); }
    	ks = YamlConfiguration.loadConfiguration(ksfile);
    }

}
