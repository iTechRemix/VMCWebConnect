package com.vaxocgaming.plugins.bukkit.vmcwebconnect;

import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.permission.Permission;

public class VMCWebConnectPermissionHandler {

    VMCWebConnect plugin;
    public static Permission perms = null;
    
    public VMCWebConnectPermissionHandler(VMCWebConnect instance) {
	this.plugin = instance;
    }
    
    public void setupPermissions() {
	RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
	perms = rsp.getProvider();
    }
}
