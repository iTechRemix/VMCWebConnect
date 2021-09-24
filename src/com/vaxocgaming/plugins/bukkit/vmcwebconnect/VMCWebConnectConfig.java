package com.vaxocgaming.plugins.bukkit.vmcwebconnect;

import org.bukkit.configuration.file.FileConfiguration;

public class VMCWebConnectConfig {
    VMCWebConnect plugin;
    FileConfiguration config;
    
    public VMCWebConnectConfig(VMCWebConnect instance) {
	this.plugin = instance;
	
	// Attempt to load config if it exists
	this.config = this.plugin.getConfig();
    }
    
    
    // Load default values until the user configures the generated YML file
    public void setupConfig() {
	this.config.addDefault("SQL Host", "localhost");
	this.config.addDefault("SQL Port", "3306");
	this.config.addDefault("SQL Database", "dbname");
	this.config.addDefault("SQL Username", "user");
	this.config.addDefault("SQL Password", "password");
	this.config.addDefault("Non-Verified Group Name", "Member");
	this.config.addDefault("Verified Group Name", "Verified");
	this.config.options().copyDefaults(true);
	this.plugin.saveConfig();
    }
}
