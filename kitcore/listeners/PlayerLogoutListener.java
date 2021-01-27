package me.yochran.kitcore.listeners;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.KitPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLogoutListener implements Listener {

    private KitCore plugin;

    public PlayerLogoutListener() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    KitPvP kitpvp = new KitPvP();

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (kitpvp.isInCombat(player)) {
            kitpvp.removeCombat(player);
            plugin.combatLogged.add(player.getUniqueId());
        }
    }
}
