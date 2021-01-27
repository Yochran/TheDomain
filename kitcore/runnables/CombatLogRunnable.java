package me.yochran.kitcore.runnables;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatLogRunnable extends BukkitRunnable {

    private KitCore plugin;

    public CombatLogRunnable() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    KitPvP kitpvp = new KitPvP();

    @Override
    public void run() {
        for (Player onlinePlayers: Bukkit.getOnlinePlayers()) {
            if (KitPvP.isKitpvpServer(onlinePlayers.getWorld())) {
                if (kitpvp.isInCombat(onlinePlayers)) {
                    if (kitpvp.getCombatTime(onlinePlayers) <= 0) {
                        plugin.combat.remove(onlinePlayers.getUniqueId());
                        Utils.sendMessage(onlinePlayers, "&7You are no longer in combat.");
                    } else {
                        plugin.combat.put(onlinePlayers.getUniqueId(), kitpvp.getCombatTime(onlinePlayers) - 1);
                    }
                }
            }
        }
    }
}
