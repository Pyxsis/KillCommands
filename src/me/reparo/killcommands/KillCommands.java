package me.reparo.killcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.reparo.killcommands.commands.CurrentStreak;
import me.reparo.killcommands.commands.SetUpNewKillConfig;
import me.reparo.killcommands.utilities.ExecutingCommands;
import me.reparo.killcommands.utilities.Util;

public class KillCommands extends JavaPlugin implements Listener{
	
	public static KillCommands kc;
	
	public void onEnable() {
		kc = this;
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getCommand("currentstreak").setExecutor(new CurrentStreak());
		getCommand("setupnewkillconfig").setExecutor(new SetUpNewKillConfig());
		saveDefaultConfig();
		Util.util.setupKillCommandsYAML();
	}

	  @EventHandler
		public void deathEvent(EntityDeathEvent e) {
		  Player killer = e.getEntity().getKiller();
		  if(e.getEntity().getKiller() != null) {
			  if(Util.killtracker.containsKey(killer.getName())) {
				  Bukkit.broadcastMessage("a" + killer.getName());
				  int currentkills = Util.killtracker.get(killer.getName());
				  Util.killtracker.remove(killer.getName());
				  Util.killtracker.put(killer.getName(), currentkills+1);
				  ExecutingCommands.executeCommands(killer, e.getEntity(), "" + Util.killtracker.get(killer.getName()));
			  } else {
				  Bukkit.broadcastMessage("b" + killer.getName());
				  Util.killtracker.put(killer.getName(), 1);
				  ExecutingCommands.executeCommands(killer, e.getEntity(), "" + Util.killtracker.get(killer.getName()));
			  }  
		  }
		}
	  
	  @EventHandler
	  public void logOut(PlayerQuitEvent e) {
		  Player killer = e.getPlayer();
		  if(Util.clearStreakOnLogout()) {
			  if(Util.killtracker.containsKey(killer.getName())) {
				  Util.killtracker.remove(killer.getName());
			  }
		  }
	  }
}