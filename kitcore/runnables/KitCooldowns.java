package me.yochran.kitcore.runnables;

import me.yochran.kitcore.KitCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class KitCooldowns extends BukkitRunnable {

    private KitCore plugin;

    public KitCooldowns() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.pvpCooldown.containsKey(onlinePlayers.getUniqueId())) {
                if (plugin.pvpCooldown.get(onlinePlayers.getUniqueId()) <= 0) {
                    plugin.pvpCooldown.remove(onlinePlayers.getUniqueId());
                } else {
                    plugin.pvpCooldown.put(onlinePlayers.getUniqueId(), plugin.pvpCooldown.get(onlinePlayers.getUniqueId()) - 1);
                }
            }

            if (plugin.fishermanCooldown.containsKey(onlinePlayers.getUniqueId())) {
                if (plugin.fishermanCooldown.get(onlinePlayers.getUniqueId()) <= 0) {
                    plugin.fishermanCooldown.remove(onlinePlayers.getUniqueId());
                } else {
                    plugin.fishermanCooldown.put(onlinePlayers.getUniqueId(), plugin.fishermanCooldown.get(onlinePlayers.getUniqueId()) - 1);
                }
            }

            if (plugin.endermanCooldown.containsKey(onlinePlayers.getUniqueId())) {
                if (plugin.endermanCooldown.get(onlinePlayers.getUniqueId()) <= 0) {
                    plugin.endermanCooldown.remove(onlinePlayers.getUniqueId());
                } else {
                    plugin.endermanCooldown.put(onlinePlayers.getUniqueId(), plugin.endermanCooldown.get(onlinePlayers.getUniqueId()) - 1);
                }
            }
        }
    }
}
