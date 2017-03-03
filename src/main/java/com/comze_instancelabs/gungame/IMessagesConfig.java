package com.comze_instancelabs.gungame;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.config.MessagesConfig;

public class IMessagesConfig extends MessagesConfig {

	public String broadcast_next_map = "&6Next map on map rotation in &c10 &6seconds!";
	public String upgrade = "&aYou got an upgrade: <level>!";
	public String sb_your_level = "Your Level";
	public String sb_gunpoints = "Gunpoints";
	public String sb_players = "Players";
	public String sb_title = "[%player%]";
	public String sb_name_level = "&bLv";

	public IMessagesConfig(JavaPlugin arg0) {
		super(arg0);

		this.getConfig().addDefault("messages.broadcast_next_map", broadcast_next_map);
		this.getConfig().addDefault("messages.upgrade", upgrade);

		this.getConfig().addDefault("messages.sb_your_level", sb_your_level);
		this.getConfig().addDefault("messages.sb_gunpoints", sb_gunpoints);
		this.getConfig().addDefault("messages.sb_players", sb_players);
		this.getConfig().addDefault("messages.sb_name_level", sb_name_level);
		this.getConfig().addDefault("messages.sb_title", sb_title);

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();

		this.broadcast_next_map = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.broadcast_next_map"));
		this.upgrade = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.upgrade"));
		this.sb_your_level = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.sb_your_level"));
		this.sb_gunpoints = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.sb_gunpoints"));
		this.sb_players = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.sb_players"));
		this.sb_name_level = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.sb_name_level"));
		this.sb_title = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.sb_title"));

	}

}
