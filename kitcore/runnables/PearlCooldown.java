package me.yochran.kitcore.runnables;

import me.yochran.kitcore.KitCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PearlCooldown extends BukkitRunnable {

    private KitCore plugin;

    public PearlCooldown() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.pearlCooldown.containsKey(onlinePlayers.getUniqueId())) {
                if (plugin.pearlCooldown.get(onlinePlayers.getUniqueId()) <= 0) {
                    plugin.pearlCooldown.remove(onlinePlayers.getUniqueId());
                } else {
                    plugin.pearlCooldown.put(onlinePlayers.getUniqueId(), plugin.pearlCooldown.get(onlinePlayers.getUniqueId()) - 1);
                }
            }
        }
    }
}
