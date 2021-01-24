package me.yochran.kitcore.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitPvP {

    public static boolean isKitpvpServer(World world) {
        if (world.getName().equalsIgnoreCase("kitpvp")) {
            return true;
        } else {
            return false;
        }
    }
}
