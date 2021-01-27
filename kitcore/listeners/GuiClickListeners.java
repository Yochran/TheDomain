package me.yochran.kitcore.listeners;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.Kits;
import me.yochran.kitcore.utils.Utils;
import me.yochran.kitcore.utils.XMaterial;
import net.vectromc.vbasic.management.EconomyManagement;
import net.vectromc.vbasic.vBasic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiClickListeners implements Listener {

    Kits kits = new Kits();
    EconomyManagement economy = new EconomyManagement();

    private KitCore plugin;
    private vBasic basic;

    public GuiClickListeners() {
        plugin = KitCore.getPlugin(KitCore.class);
        basic = vBasic.getPlugin(vBasic.class);
    }

    @EventHandler
    public void onGuiClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(Utils.translate("&6&lKit Selector"))) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
                if (event.getCurrentItem().getType() == XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial() || event.getCurrentItem().getType() == XMaterial.BEDROCK.parseMaterial()) {
                    event.setCancelled(true);
                } else {
                    if (event.getCurrentItem().getType() == XMaterial.DIAMOND_SWORD.parseMaterial()) {
                        kits.pvpKit(player);
                    } else if (event.getCurrentItem().getType() == XMaterial.FISHING_ROD.parseMaterial()) {
                        kits.fishermanKit(player);
                    } else if (event.getCurrentItem().getType() == XMaterial.ENDER_PEARL.parseMaterial()) {
                        kits.endermanKit(player);
                    } else if (event.getCurrentItem().getType() == XMaterial.BOW.parseMaterial()) {
                        kits.archerKit(player);
                    } else if (event.getCurrentItem().getType() == XMaterial.SUGAR.parseMaterial()) {
                        kits.rogueKit(player);
                    }
                }
            }
        } else if (event.getView().getTitle().equalsIgnoreCase(Utils.translate("&a&lKit Shop"))) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
                if (event.getCurrentItem().getType() == XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial() || event.getCurrentItem().getType() == XMaterial.BEDROCK.parseMaterial()) {
                    event.setCancelled(true);
                } else {
                    if (event.getCurrentItem().getType() == XMaterial.FISHING_ROD.parseMaterial()) {
                        if (plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Fisherman")) {
                            event.setCancelled(true);
                            player.closeInventory();
                            Utils.sendMessage(player, "&cYou already own this kit.");
                        } else {
                            if (basic.economy.config.getDouble(player.getUniqueId().toString() + ".kitpvp.Balance") < 5000.0) {
                                event.setCancelled(true);
                                player.closeInventory();
                                Utils.sendMessage(player, "&cYou don't have enough money to buy this kit.");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                Utils.sendMessage(player, "&aYou have bought the &bFisherman &akit.");
                                economy.removeMoney("kitpvp", player, 5000.0);
                                plugin.data.config.set(player.getUniqueId().toString() + ".Fisherman", true);
                                plugin.data.saveData();
                            }
                        }
                    } else if (event.getCurrentItem().getType() == XMaterial.ENDER_PEARL.parseMaterial()) {
                        if (plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Enderman")) {
                            event.setCancelled(true);
                            player.closeInventory();
                            Utils.sendMessage(player, "&cYou already own this kit.");
                        } else {
                            if (basic.economy.config.getDouble(player.getUniqueId().toString() + ".kitpvp.Balance") < 6500.0) {
                                event.setCancelled(true);
                                player.closeInventory();
                                Utils.sendMessage(player, "&cYou don't have enough money to buy this kit.");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                Utils.sendMessage(player, "&aYou have bought the &5Enderman &akit.");
                                economy.removeMoney("kitpvp", player, 6500.0);
                                plugin.data.config.set(player.getUniqueId().toString() + ".Enderman", true);
                                plugin.data.saveData();
                            }
                        }
                    } else if (event.getCurrentItem().getType() == XMaterial.SUGAR.parseMaterial()) {
                        if (plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Rogue")) {
                            event.setCancelled(true);
                            player.closeInventory();
                            Utils.sendMessage(player, "&cYou already own this kit.");
                        } else {
                            if (basic.economy.config.getDouble(player.getUniqueId().toString() + ".kitpvp.Balance") < 3500.0) {
                                event.setCancelled(true);
                                player.closeInventory();
                                Utils.sendMessage(player, "&cYou don't have enough money to buy this kit.");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                Utils.sendMessage(player, "&aYou have bought the &8Rogue &akit.");
                                economy.removeMoney("kitpvp", player, 3500.0);
                                plugin.data.config.set(player.getUniqueId().toString() + ".Rogue", true);
                                plugin.data.saveData();
                            }
                        }
                    }
                }
            }
        }
    }
}
