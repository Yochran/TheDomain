package me.yochran.kitcore.listeners;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.XMaterial;
import net.vectromc.vbasic.management.EconomyManagement;
import net.vectromc.vbasic.management.StatManagement;
import net.vectromc.vbasic.utils.Utils;
import net.vectromc.vbasic.vBasic;
import net.vectromc.vnitrogen.vNitrogen;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;

import java.text.DecimalFormat;

public class WorldChangeListener implements Listener {

    private KitCore plugin;
    private vBasic basic;
    private vNitrogen nitrogen;

    public WorldChangeListener() {
        plugin = KitCore.getPlugin(KitCore.class);
        basic = vBasic.getPlugin(vBasic.class);
        nitrogen = vNitrogen.getPlugin(vNitrogen.class);
    }

    KitPvP kitpvp = new KitPvP();

    StatManagement stats = new StatManagement();
    EconomyManagement economy = new EconomyManagement();

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (KitPvP.isKitpvpServer(player.getWorld())) {
            if (plugin.combatLogged.contains(player.getUniqueId())) {
                player.getInventory().clear();
                player.getInventory().setHelmet(XMaterial.AIR.parseItem());
                player.getInventory().setChestplate(XMaterial.AIR.parseItem());
                player.getInventory().setLeggings(XMaterial.AIR.parseItem());
                player.getInventory().setBoots(XMaterial.AIR.parseItem());
                plugin.endermanKit.remove(player.getUniqueId());
                // Stats
                OfflinePlayer toReceive = Bukkit.getOfflinePlayer(plugin.combatDuo.get(player.getUniqueId()));
                String displayName = "";
                for (String rank : nitrogen.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
                    if (nitrogen.pData.config.getString(toReceive.getUniqueId().toString() + ".Rank").equalsIgnoreCase(rank)) {
                        displayName = nitrogen.getConfig().getString("Ranks." + rank + ".color") + toReceive.getName();
                    }
                }
                DecimalFormat df = new DecimalFormat("###,###,###,###,###,###.##");
                double amount = economy.getMoneyPerKill();
                economy.addMoney(player.getWorld().getName(), toReceive, amount);
                if (economy.isBountied(player.getWorld().getName(), player)) {
                    double bounty = economy.getBountyAmount(player.getWorld().getName(), player);
                    economy.claimBounty(player.getWorld().getName(), player, toReceive, bounty);
                    for (Player onlinePlayers : Bukkit.getWorld(player.getWorld().getName()).getPlayers()) {
                        Utils.sendMessage(onlinePlayers, basic.getConfig().getString("Bounty.Completed")
                                .replace("%player%", displayName)
                                .replace("%target%", player.getDisplayName())
                                .replace("%amount%", df.format(bounty)));
                    }
                }
                stats.addKill(player.getWorld().getName(), toReceive);
                stats.addDeath(player.getWorld().getName(), player);
                stats.endStreak(player.getWorld().getName(), player);
                stats.addToStreak(player.getWorld().getName(), player);
                stats.updateKDR(player.getWorld().getName(), toReceive);
                stats.updateKDR(player.getWorld().getName(), player);
                plugin.combat.remove(player.getUniqueId());
                plugin.combatDuo.remove(player.getUniqueId());
                plugin.combatLogged.remove(player.getUniqueId());
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }
        }
    }
}
