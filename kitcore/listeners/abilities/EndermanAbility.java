package me.yochran.kitcore.listeners.abilities;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.XMaterial;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

public class EndermanAbility implements Listener {

    private KitCore plugin;

    public EndermanAbility() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @EventHandler
    public void onEnderpearlThrow(ProjectileLaunchEvent event) {
        ProjectileSource shotta = event.getEntity().getShooter();
        if (shotta instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if (event.getEntity() instanceof EnderPearl) {
                if (plugin.endermanKit.contains(player.getUniqueId())) {
                    if (!plugin.pearlCooldown.containsKey(player.getUniqueId())) {
                        player.getInventory().addItem(XMaterial.ENDER_PEARL.parseItem());
                    }
                }
            }
        }
    }
}
