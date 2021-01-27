package me.yochran.kitcore.listeners;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private KitCore plugin;

    public CommandListener() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    KitPvP kitpvp = new KitPvP();

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (KitPvP.isKitpvpServer(player.getWorld())) {
            if (kitpvp.isInCombat(player)) {
                for (String commands : plugin.getConfig().getStringList("CombatLog.BlacklistedCommands")) {
                    if (event.getMessage().contains(commands)) {
                        event.setCancelled(true);
                        Utils.sendMessage(player, "&7You cannot use that command while in combat.");
                    }
                }
            }
        }
    }
}
