package com.thecloudyco.staffchat.handler;

import com.thecloudyco.staffchat.util.C;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginHandler implements Listener {

    public static Map<UUID, Boolean> InStaffChat = new HashMap<>();
    public static Map<UUID, Boolean> InAdminChat = new HashMap<>();

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        if(InStaffChat.get(event.getPlayer().getUniqueId())) {
            if(InStaffChat.get(event.getPlayer().getUniqueId())) {
                // Disable Admin Chat.
                toggleAdminChat(event.getPlayer());
            }
            event.getPlayer().sendMessage(C.cGold + "You are still talking in staff chat!");
        }
        if(InAdminChat.get(event.getPlayer().getUniqueId())) {
            event.getPlayer().sendMessage(C.cGold + "You are still talking in admin chat!");
        }
    }

    public static boolean toggleStaffChat(Player player) {
        if(!InStaffChat.containsKey(player.getUniqueId())) {
            InStaffChat.put(player.getUniqueId(), true);
            return true;
        } else {
            if(InStaffChat.get(player.getUniqueId())) {
                InStaffChat.put(player.getUniqueId(), false);
                return false;
            } else {
                InStaffChat.put(player.getUniqueId(), true);
                return true;
            }
        }
    }

    public static boolean toggleAdminChat(Player player) {
        if(!InAdminChat.containsKey(player.getUniqueId())) {
            InAdminChat.put(player.getUniqueId(), true);
            return true;
        } else {
            if(InAdminChat.get(player.getUniqueId())) {
                InAdminChat.put(player.getUniqueId(), false);
                return false;
            } else {
                InAdminChat.put(player.getUniqueId(), true);
                return true;
            }
        }
    }
}
