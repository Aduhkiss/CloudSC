package com.thecloudyco.staffchat.cmd;

import com.thecloudyco.staffchat.StaffChat;
import com.thecloudyco.staffchat.handler.LoginHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 || args == null) {
            // Toggle Admin Chat
            // THIS WILL break if CONSOLE runs just /sc with NO arguments!!!
            if(LoginHandler.toggleAdminChat((Player) sender)) {
                sender.sendMessage(ChatColor.RED + "You are now talking in Admin Chat!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You are no longer talking in Admin Chat!");
                return true;
            }
        } else {
            StaffChat.getInstance().adminChat(sender.getName(), combine(args, 0));
            Bukkit.getLogger().info("[CLOUD ADMIN LOGS]: (" + sender.getName() + "): " + combine(args, 0));
            return true;
        }
    }

    public static String combine(String[] arr, int startPos) {
        StringBuilder str = new StringBuilder();

        for(int i = startPos; i < arr.length; ++i) {
            str = str.append(arr[i] + " ");
        }
        return str.toString();
    }
}
