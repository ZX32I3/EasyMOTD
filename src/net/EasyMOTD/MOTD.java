package net.EasyMOTD;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener {
	
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		int Playercount = e.getNumPlayers();
		e.setMaxPlayers(1 + Playercount);
		e.setMotd(ChatColor.translateAlternateColorCodes('&', "&6&lZotex&f&lMC Network" + "\nl&r" + "" + "&b&lSale or some shit"));
		
		
	}
	

}

