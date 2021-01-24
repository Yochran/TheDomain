package me.yochran.hubcore.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(translate(message));
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(translate(message));
    }

    public static void liner(Player player) {
        player.sendMessage(translate("&7&m----------------------"));
    }

    public static void liner(CommandSender sender) {
        sender.sendMessage(translate("&7&m----------------------"));
    }

    public static void spacer(Player player) {
        player.sendMessage(translate("&7 "));
    }

    public static void spacer(CommandSender sender) {
        sender.sendMessage(translate("&7 "));
    }
}
