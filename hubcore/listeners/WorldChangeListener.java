package me.yochran.hubcore.listeners;

import me.yochran.hubcore.HubCore;
import me.yochran.hubcore.Utils.Hub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class WorldChangeListener implements Listener {

    private HubCore plugin;

    public WorldChangeListener() {
        plugin = HubCore.getPlugin(HubCore.class);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        List<String> hubs = plugin.getConfig().getStringList("HubServers");
        if (hubs.contains(player.getWorld().getName())) {
            new BukkitRunnable() {
                public void run() {
                    Hub.giveItems(player);
                }
            }.runTaskLater(plugin, 20);
        }
    }
}
