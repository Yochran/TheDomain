package me.yochran.kitcore.listeners;

import me.yochran.kitcore.KitCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private KitCore plugin;

    public PlayerJoinListener() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!plugin.data.config.contains(player.getUniqueId().toString())) {
            plugin.data.config.set(player.getUniqueId().toString() + ".Fisherman", false);
            plugin.data.config.set(player.getUniqueId().toString() + ".Enderman", false);
            plugin.data.config.set(player.getUniqueId().toString() + ".Rogue", false);
            plugin.data.saveData();
        }
    }
}
