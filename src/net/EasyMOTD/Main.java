package net.EasyMOTD;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, CommandExecutor {	
	
	@Override
	public void onEnable() {
		System.out.println("[EasyMOTD] Plugin has been enabled successfully.");
		getServer().getPluginManager().registerEvents(this, this);
		
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();
	}
		
		// Server MOTD Configuration	
	
		@EventHandler
		public void onPing(ServerListPingEvent e) {
			String line1 = this.getConfig().getString("line-1");
			String line2 = this.getConfig().getString("line-2");
			int maxplayercount = this.getConfig().getInt("max-playercount");
			
			e.setMotd(ChatColor.translateAlternateColorCodes('&', line1 + "\n" + line2 ));
			
			// Server Playercount Configuration	
			
			if (getConfig().getBoolean("fancy-playercount")) {
			int Playercount = e.getNumPlayers();
			e.setMaxPlayers(1 + Playercount);
		
			} else {
				e.setMaxPlayers(maxplayercount);}
			}
		
		// Server Maintenance Configuration
		@EventHandler
		public void onJoin(PlayerJoinEvent e) {
			if (getConfig().getBoolean("maintenance-active") == true) {
				Player p = e.getPlayer();
				if(!(p.hasPermission("easymotd.maintenance.bypass"))) {
					String mr = this.getConfig().getString("maintenance-reason");
					mr = mr.replace("%nl%", "\n" );
					mr = mr.replace("%player%", p.getName());
					p.kickPlayer(ChatColor.translateAlternateColorCodes('&', mr ));
					System.out.println("<!> " + p + " tried to join, but the server is in maintenance mode!");
				}
			}
		}
		
		// EasyMOTD Reload Command
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
			if (cmd.getName().equalsIgnoreCase("easymotd")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						if (sender.hasPermission("easymotd.reload")) {
							reloadConfig();
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lEasy&f&lMOTD&8] &ePlugin reloaded successfully!"));
							return true;
						}
			        } else {
			        	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lEasy&f&lMOTD&8] &eInvalid usage, use /easymotd reload"));
			        }

				} else {
			        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lEasy&f&lMOTD&8] &eInvalid usage, use /easymotd reload"));
			      }

			    }
			return false;
			
		}
		
	}


