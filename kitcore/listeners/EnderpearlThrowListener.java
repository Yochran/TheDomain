package me.yochran.kitcore.listeners;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.Console;
import me.yochran.kitcore.utils.Utils;
import me.yochran.kitcore.utils.XMaterial;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

public class EnderpearlThrowListener implements Listener {

    private KitCore plugin;

    public EnderpearlThrowListener() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @EventHandler
    public void onEnderpearlThrow(ProjectileLaunchEvent event) {
        ProjectileSource shooter = event.getEntity().getShooter();
        if (shooter instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if (event.getEntity() instanceof EnderPearl) {
                if (plugin.pearlCooldown.containsKey(player.getUniqueId())) {
                    event.setCancelled(true);
                    Utils.sendMessage(player, "&cYou are still on a &4&l" + plugin.pearlCooldown.get(player.getUniqueId()) + " &csecond cooldown for that.");
                    player.getInventory().addItem(XMaterial.ENDER_PEARL.parseItem());
                }
                if (!event.isCancelled()) {
                    plugin.pearlCooldown.put(player.getUniqueId(), 20);
                }
            }
        }
    }
}
