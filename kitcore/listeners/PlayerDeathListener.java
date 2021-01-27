package me.yochran.kitcore.listeners;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.XMaterial;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private KitCore plugin;

    public PlayerDeathListener() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    KitPvP kitpvp = new KitPvP();

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Entity entity = event.getEntity().getKiller();
        if (entity instanceof Player) {
            Player player = event.getEntity().getKiller();
            if (KitPvP.isKitpvpServer(player.getWorld())) {
                for (int i = 0; i < 2; i++) {
                    player.getInventory().addItem(XMaterial.GOLDEN_APPLE.parseItem());
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        plugin.endermanKit.remove(player.getUniqueId());
        kitpvp.removeCombat(player);
    }
}
