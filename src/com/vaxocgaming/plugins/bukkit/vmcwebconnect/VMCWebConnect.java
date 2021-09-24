package com.vaxocgaming.plugins.bukkit.vmcwebconnect;

import org.bukkit.plugin.java.JavaPlugin;

public class VMCWebConnect extends JavaPlugin {

    // Call functions needed on startup

    public void onEnable(){
	VMCWebConnectConfig configClass = new VMCWebConnectConfig(this);
	configClass.setupConfig();
	VMCWebConnectPermissionHandler permHandler = new VMCWebConnectPermissionHandler(this);
	permHandler.setupPermissions();
	this.getServer().getPluginManager().registerEvents(new VMCWebConnectListener(this), this);
    }
    
    public void onDisable(){
	
    }
}
