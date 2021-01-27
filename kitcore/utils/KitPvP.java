package me.yochran.kitcore.utils;

import me.yochran.kitcore.KitCore;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class KitPvP {

    private KitCore plugin;

    public KitPvP() {
        plugin = KitCore.getPlugin(KitCore.class);
    }


    public static boolean isKitpvpServer(World world) {
        if (world.getName().equalsIgnoreCase("kitpvp")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInCombat(Player player) {
        if (plugin.combat.containsKey(player.getUniqueId())) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getCombatTime(Player player) {
        return plugin.combat.get(player.getUniqueId());
    }

    public void removeCombat(Player player) {
        plugin.combat.remove(player.getUniqueId());
    }

    public void addCombat(Player player) {
        removeCombat(player);
        plugin.combat.put(player.getUniqueId(), 20);
    }
}
