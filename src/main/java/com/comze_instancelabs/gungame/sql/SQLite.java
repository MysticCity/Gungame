package com.comze_instancelabs.gungame.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import com.comze_instancelabs.minigamesapi.MinigamesAPI;

public class SQLite extends Database {
	String user = "";
	String database = "";
	String password = "";
	Connection c = null;

	public SQLite(String database, String username, String password) {
		this.database = database;
		this.user = username;
		this.password = password;
	}

	public Connection open() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:main.db");
			return c;
		} catch (SQLException e) {
			MinigamesAPI.getAPI().getLogger().log(Level.SEVERE, "Could not connect to SQLite database! Cause: ", e);
		} catch (ClassNotFoundException e) {
			MinigamesAPI.getAPI().getLogger().log(Level.SEVERE, "JDBC Driver not found!");
		}
		return this.c;
	}

	public boolean checkConnection() {
		if (this.c != null) {
			return true;
		}
		return false;
	}

	public Connection getConn() {
		return this.c;
	}

	public void closeConnection(Connection c) {
		c = null;
	}
}
