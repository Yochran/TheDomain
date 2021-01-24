package me.yochran.kitcore.listeners.abilities.specialitems;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.Console;
import me.yochran.kitcore.utils.Utils;
import me.yochran.kitcore.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WindStaffAbility implements Listener {

    private KitCore plugin;

    public WindStaffAbility() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInHand().getType() == XMaterial.BLAZE_ROD.parseMaterial()) {
            if (plugin.windstaffCooldown.containsKey(player.getUniqueId())) {
                Utils.sendMessage(player, "&cYou are on a &4&l" + plugin.windstaffCooldown.get(player.getUniqueId()) + " &csecond cooldown for that.");
            } else {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (target != player) {
                        if (player.getLocation().distanceSquared(target.getLocation()) < 100) {
                            double X = target.getLocation().getDirection().getX() * -10.0;
                            double Z = target.getLocation().getDirection().getZ() * -10.0;
                            double height = 3.5;
                            org.bukkit.util.Vector unitVector = new org.bukkit.util.Vector(X, height, Z);
                            unitVector = unitVector.normalize();
                            target.setVelocity(unitVector.multiply(4.1));
                        }
                    }
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        plugin.windstaffCooldown.put(player.getUniqueId(), 30);
                    }
                }.runTaskLater(plugin, 5);
            }
        }
    }
}
