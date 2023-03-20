package com.thecloudyco.staffchat.handler;

import com.thecloudyco.staffchat.StaffChat;
import com.thecloudyco.staffchat.util.C;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {
    @EventHandler
    public void onChatted(AsyncPlayerChatEvent event) {

        if((LoginHandler.InStaffChat.containsKey(event.getPlayer().getUniqueId()) && LoginHandler.InStaffChat.get(event.getPlayer().getUniqueId())) &&
        LoginHandler.InAdminChat.containsKey(event.getPlayer().getUniqueId()) && LoginHandler.InAdminChat.get(event.getPlayer().getUniqueId())) {
            // You are currently in BOTH chats. this is NOT allowed. just remove them from Admin Only
            event.getPlayer().sendMessage(C.cRed + "Uh oh! It looks like you are in STAFF and ADMIN! You can't do that! Resetting to only Staff Chat...");
            LoginHandler.InAdminChat.put(event.getPlayer().getUniqueId(), false);
        }


        if(LoginHandler.InStaffChat.containsKey(event.getPlayer().getUniqueId()) && LoginHandler.InStaffChat.get(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            StaffChat.getInstance().staffChat(event.getPlayer().getName(), event.getMessage());
        }

        if(LoginHandler.InAdminChat.containsKey(event.getPlayer().getUniqueId()) && LoginHandler.InAdminChat.get(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            StaffChat.getInstance().adminChat(event.getPlayer().getName(), event.getMessage());
        }
    }
}
