package me.yochran.kitcore.listeners;

import me.yochran.kitcore.utils.KitPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerListener implements Listener {

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        if (KitPvP.isKitpvpServer(player.getWorld())) {
            event.setCancelled(true);
            player.setFoodLevel(20);
        }
    }
}
