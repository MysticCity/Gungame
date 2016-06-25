package com.comze_instancelabs.gungame;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.ArenaConfigStrings;
import com.comze_instancelabs.minigamesapi.config.DefaultConfig;

public class IDefaultConfig extends DefaultConfig {

	public IDefaultConfig(JavaPlugin plugin) {
		super(plugin, false);
		IDefaultConfig.init(plugin);
	}

	public static void init(JavaPlugin plugin) {
		FileConfiguration config = plugin.getConfig();
		config.addDefault("config.classes_selection_item", 399);
		config.addDefault("config.default_max_players", 4);
		config.addDefault("config.default_min_players", 2);
		config.addDefault("config.lobby_countdown", 0);
		config.addDefault("config.ingame_countdown", 0);
		config.addDefault(ArenaConfigStrings.CONFIG_REWARDS_ECONOMY, true);
		config.addDefault(ArenaConfigStrings.CONFIG_REWARDS_ECONOMY_REWARD, 10);
		config.addDefault(ArenaConfigStrings.CONFIG_REWARDS_ITEM_REWARD, false);
		config.addDefault(ArenaConfigStrings.CONFIG_REWARDS_ITEM_REWARD_IDS, "264*1;11*1");
		config.addDefault(ArenaConfigStrings.CONFIG_REWARDS_COMMAND_REWARD, false);
		config.addDefault(ArenaConfigStrings.CONFIG_REWARDS_COMMAND, "pex user <player> add SKILLZ.*");
		config.addDefault(ArenaConfigStrings.CONFIG_ARCADE_MIN_PLAYERS, 1);
		config.addDefault(ArenaConfigStrings.CONFIG_ARCADE_MAX_PLAYERS, 16);
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}

}
