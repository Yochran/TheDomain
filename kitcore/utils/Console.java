package me.yochran.kitcore.utils;

import org.bukkit.Bukkit;

public class Console {

    public static void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(Utils.translate(message));
    }
}
