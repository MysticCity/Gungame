package com.comze_instancelabs.gungame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.comze_instancelabs.minigamesapi.Arena;
import com.comze_instancelabs.minigamesapi.ArenaConfigStrings;
import com.comze_instancelabs.minigamesapi.ArenaType;
import com.comze_instancelabs.minigamesapi.MinigamesAPI;
import com.comze_instancelabs.minigamesapi.PluginInstance;

public class IArena extends Arena {

	public Main m;
	public PluginInstance pli;

	HashMap<String, Integer> pspawn = new HashMap<String, Integer>(); // player -> spawn id

	boolean cteam = true;

	boolean started_map_rotation = false;
	
	public IArena(Main m, String arena_id) {
		super(m, arena_id, ArenaType.JUMPNRUN);
		MinigamesAPI.getAPI();
		pli = MinigamesAPI.pinstances.get(m);
		this.m = m;
		this.setAlwaysPvP(true);
	}

	@Override
	public void spectate(String playername) {

	}

	ArrayList<BukkitTask> tt = new ArrayList<BukkitTask>();
	int currentingamecount;

	@Override
	public void joinPlayerLobby(String p_) {
		super.joinPlayerLobby(p_);
		m.lv.put(p_, 0);
		Player p = Bukkit.getPlayer(p_);
		for (ItemStack item : m.start_items) {
			p.getInventory().addItem(item);
		}
		p.updateInventory();
		m.addextraitems(p);
		m.scoreboard.updateScoreboard(this);

		p.setGameMode(GameMode.SURVIVAL);

		final IArena a = this;
		if (!started_map_rotation) {
			this.getPlugin().getLogger().fine("t2");
			// Map rotation
			if (m.getConfig().getBoolean(ArenaConfigStrings.CONFIG_MAP_ROTATION)) {
				this.getPlugin().getLogger().fine("t");
				tt.add(Bukkit.getScheduler().runTaskLater(m, new Runnable() {
					public void run() {
						final ArrayList<String> temp = new ArrayList<String>(a.getAllPlayers());
						Bukkit.getScheduler().runTaskLater(m, new Runnable() {
							public void run() {
								a.nextArenaOnMapRotation(temp);
							}
						}, 50L);
						a.stop();
					}
				}, m.getConfig().getInt("config.map_rotation_time_minutes") * 20 * 60));

				tt.add(Bukkit.getScheduler().runTaskLater(m, new Runnable() {
					public void run() {
						for (String p_ : a.getAllPlayers()) {
							Player p = Bukkit.getPlayer(p_);
							if (p != null) {
								p.sendMessage(m.im.broadcast_next_map);
							}
						}
					}
				}, m.getConfig().getInt("config.map_rotation_time_minutes") * 20 * 60 - 20 * 10));
			}
			started_map_rotation = true;
		}
	}

	@Override
	public void leavePlayer(final String playername, boolean fullLeave) {
		super.leavePlayer(playername, fullLeave);
		if (pspawn.containsKey(playername)) {
			pspawn.remove(playername);
		}
	}

	@Override
	public void stop() {
		started_map_rotation = false;
		super.stop();
		for (BukkitTask t : tt) {
			t.cancel();
		}
	}

}
