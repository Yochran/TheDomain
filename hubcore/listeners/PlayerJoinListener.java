package me.yochran.hubcore.listeners;

import me.yochran.hubcore.HubCore;
import me.yochran.hubcore.Utils.Hub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {

    private HubCore plugin;

    public PlayerJoinListener() {
        plugin = HubCore.getPlugin(HubCore.class);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            public void run() {
                Hub.giveItems(player);
            }
        }.runTaskLater(plugin, 20);
    }
}
