package me.yochran.kitcore.listeners;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.Utils;
import net.vectromc.vnitrogen.vNitrogen;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerAttackListener implements Listener {

    private KitCore plugin;
    private vNitrogen nitrogen;

    public PlayerAttackListener() {
        plugin = KitCore.getPlugin(KitCore.class);
        nitrogen = vNitrogen.getPlugin(vNitrogen.class);
    }

    KitPvP kitpvp = new KitPvP();

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!event.isCancelled()) {
            Entity a = event.getEntity();
            Entity b = event.getDamager();
            if (a instanceof Player && b instanceof Player) {
                Player victim = (Player) event.getEntity();
                Player attacker = (Player) event.getDamager();
                if (!kitpvp.isInCombat(victim)) {
                    nitrogen.setPlayerColor(attacker);
                    Utils.sendMessage(victim, "&7You have been tagged by &b" + attacker.getDisplayName() + "&7.");
                }
                if (!kitpvp.isInCombat(attacker)) {
                    nitrogen.setPlayerColor(victim);
                    Utils.sendMessage(attacker, "&7You have tagged &b" + victim.getDisplayName() + "&7.");
                }
                kitpvp.addCombat(victim);
                kitpvp.addCombat(attacker);

                plugin.combatDuo.remove(victim.getUniqueId());
                plugin.combatDuo.put(victim.getUniqueId(), attacker.getUniqueId());
            }
        }
    }
}
