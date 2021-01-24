package me.yochran.kitcore.runnables;

import me.yochran.kitcore.KitCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WindStaffCooldown extends BukkitRunnable {

    private KitCore plugin;

    public WindStaffCooldown() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.windstaffCooldown.containsKey(onlinePlayers.getUniqueId())) {
                if (plugin.windstaffCooldown.get(onlinePlayers.getUniqueId()) <= 0) {
                    plugin.windstaffCooldown.remove(onlinePlayers.getUniqueId());
                } else {
                    plugin.windstaffCooldown.put(onlinePlayers.getUniqueId(), plugin.windstaffCooldown.get(onlinePlayers.getUniqueId()) - 1);
                }
            }
        }
    }
}
