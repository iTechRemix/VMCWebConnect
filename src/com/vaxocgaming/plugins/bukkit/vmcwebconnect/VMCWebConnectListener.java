package com.vaxocgaming.plugins.bukkit.vmcwebconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VMCWebConnectListener implements Listener {
    
    VMCWebConnect plugin;
    
    public VMCWebConnectListener(VMCWebConnect instance){
	this.plugin = instance;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
	if(verifyPlayer(e.getPlayer().getName())){ //If player successfully verifies
	    if(VMCWebConnectPermissionHandler.perms.getPrimaryGroup(e.getPlayer()).equalsIgnoreCase(this.plugin.getConfig().getString("Non-Verified Group Name"))){ //If player is a Member
		VMCWebConnectPermissionHandler.perms.playerAddGroup(e.getPlayer(), this.plugin.getConfig().getString("Verified Group Name"));
		VMCWebConnectPermissionHandler.perms.playerRemoveGroup(e.getPlayer(), this.plugin.getConfig().getString("Non-Verified Group Name"));
	    }
	} else { //If player is not verified
	    if(VMCWebConnectPermissionHandler.perms.getPrimaryGroup(e.getPlayer()).equalsIgnoreCase(this.plugin.getConfig().getString("Verified Group Name"))){
		VMCWebConnectPermissionHandler.perms.playerAddGroup(e.getPlayer(), this.plugin.getConfig().getString("Non-Verified Group Name"));
		VMCWebConnectPermissionHandler.perms.playerRemoveGroup(e.getPlayer(), this.plugin.getConfig().getString("Verified Group Name"));
	    }
	}
    }
    
    private boolean verifyPlayer(String playerName){
	Connection con;
	ResultSet rs;
	String jdbcUrl = "jdbc:mysql://" + this.plugin.getConfig().getString("SQL Host") + ":" + this.plugin.getConfig().getString("SQL Port") + "/" +  this.plugin.getConfig().getString("SQL Database");
	
	try{
	    System.out.println("[VMCWebConnect] Attempting to verify player " + playerName + "...");
	    con = DriverManager.getConnection(jdbcUrl, this.plugin.getConfig().getString("SQL Username"), this.plugin.getConfig().getString("SQL Password"));
	    Statement queryStatement = con.createStatement();
	    rs = queryStatement.executeQuery("SELECT * FROM xf_user_field_value WHERE field_id = 'MinecraftName' AND field_value = '" + playerName + "'");
	    System.out.println("[VMCWebConnect] Connected and retrieved lookup for " + playerName);
	    
	    if(!rs.next()){
		System.out.println("[VMCWebConnect] Player " + playerName + " was not found on the website.");
	        return false;	        
	    } else {
	        System.out.println("[VMCWebConnect] Player " + playerName + " was successfully found on website.");
	        return true;
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return false;
	}
    }
}
