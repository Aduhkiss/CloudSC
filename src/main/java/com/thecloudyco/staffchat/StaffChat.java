package com.thecloudyco.staffchat;

import com.thecloudyco.staffchat.cmd.AdminChatCmd;
import com.thecloudyco.staffchat.cmd.StaffChatCmd;
import com.thecloudyco.staffchat.handler.ChatHandler;
import com.thecloudyco.staffchat.handler.LoginHandler;
import com.thecloudyco.staffchat.util.C;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffChat extends JavaPlugin {

    private static StaffChat me;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[CLOUD STAFF CHAT]: Enabled Cloud Staff Chat. Written with <3 by Aduhkiss");
        me = this;
        // Enable Handlers
        Bukkit.getPluginManager().registerEvents(new LoginHandler(), this);
        Bukkit.getPluginManager().registerEvents(new ChatHandler(), this);

        // Enable Commands
        getCommand("staffchat").setExecutor(new StaffChatCmd());
        getCommand("adminchat").setExecutor(new AdminChatCmd());

        // Load Default config if it doesnt exist
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        me = null;
    }

    public static StaffChat getInstance() {
        return me;
    }

    public void staffChat(String caller, String message) {
        for(Player pl : Bukkit.getOnlinePlayers()) {
            if(pl.hasPermission("cloudsc.staffchat")) {
                pl.sendMessage(C.cDRed + C.Bold + "Staff " + getConfig().getString("server.name") + " " + C.cDAqua + caller + " : " + C.cRed + message);
            }
        }
    }

    public void adminChat(String caller, String message) {
        for(Player pl : Bukkit.getOnlinePlayers()) {
            if(pl.hasPermission("cloudsc.adminchat")) {
                pl.sendMessage(C.cDRed + C.Bold + "Admin Only " + getConfig().getString("server.name") + " " + C.cDAqua + caller + " : " + C.cRed + message);
            }
        }
    }
}
