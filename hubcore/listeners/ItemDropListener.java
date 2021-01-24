package me.yochran.hubcore.listeners;

import me.yochran.hubcore.Utils.Hub;
import me.yochran.hubcore.Utils.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDropListener implements Listener {

    Hub hub = new Hub();

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        if (hub.isHubServer(player.getWorld().getName())) {
            if (item.getType() == XMaterial.COMPASS.parseMaterial() || item.getType() == XMaterial.CLOCK.parseMaterial()) {
                event.setCancelled(true);
            }
        }
    }
}
