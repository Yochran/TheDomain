package me.yochran.kitcore.utils;

import me.yochran.kitcore.KitCore;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Kits {

    public KitCore plugin;

    public Kits() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    public void pvpKit(Player player) {
        player.closeInventory();
        if (plugin.pvpCooldown.containsKey(player.getUniqueId())) {
            Utils.sendMessage(player, "&cYou are on cooldown for that.");
        } else {
            player.getInventory().clear();
            ItemStack sword = XMaterial.DIAMOND_SWORD.parseItem();
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            player.getInventory().setItem(0, sword);
            player.getInventory().setHelmet(XMaterial.IRON_HELMET.parseItem());
            player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
            player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
            player.getInventory().setBoots(XMaterial.IRON_BOOTS.parseItem());
            for (int i = 0; i < 3; i++) {
                player.getInventory().addItem(XMaterial.GOLDEN_APPLE.parseItem());
            }
            Utils.sendMessage(player, "&7You have selected the &aDefault &7kit.");
            plugin.pvpCooldown.put(player.getUniqueId(), 15);
            plugin.endermanKit.remove(player.getUniqueId());
        }
    }

    public void fishermanKit(Player player) {
        player.closeInventory();
        if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Fisherman")) {
            Utils.sendMessage(player, "&cYou do not own this kit. &7(/kitshop)");
        } else {
            if (plugin.fishermanCooldown.containsKey(player.getUniqueId())) {
                Utils.sendMessage(player, "&cYou are on cooldown for that.");
            } else {
                player.getInventory().clear();
                ItemStack sword = XMaterial.IRON_SWORD.parseItem();
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                ItemStack rod = XMaterial.FISHING_ROD.parseItem();
                rod.addEnchantment(Enchantment.DURABILITY, 3);
                ItemStack helmet = XMaterial.CHAINMAIL_HELMET.parseItem();
                helmet.addEnchantment(Enchantment.DURABILITY, 2);
                ItemStack boots = XMaterial.CHAINMAIL_BOOTS.parseItem();
                boots.addEnchantment(Enchantment.DURABILITY, 2);
                boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                player.getInventory().setItem(0, sword);
                player.getInventory().setItem(1, rod);
                player.getInventory().setHelmet(helmet);
                player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                player.getInventory().setBoots(boots);
                for (int i = 0; i < 3; i++) {
                    player.getInventory().addItem(XMaterial.GOLDEN_APPLE.parseItem());
                }
                Utils.sendMessage(player, "&7You have selected the &bFisherman &7kit.");
                plugin.fishermanCooldown.put(player.getUniqueId(), 5400);
                plugin.endermanKit.remove(player.getUniqueId());
            }
        }
    }

    public void endermanKit(Player player) {
        player.closeInventory();
        if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Enderman")) {
            Utils.sendMessage(player, "&cYou do not own this kit. &7(/kitshop)");
        } else {
            if (plugin.endermanCooldown.containsKey(player.getUniqueId())) {
                Utils.sendMessage(player, "&cYou are on cooldown for that.");
            } else {
                player.getInventory().clear();
                ItemStack sword = XMaterial.GOLDEN_SWORD.parseItem();
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                sword.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
                ItemStack pearl = XMaterial.ENDER_PEARL.parseItem();
                ItemStack boots = XMaterial.LEATHER_BOOTS.parseItem();
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                bootsMeta.setColor(Color.BLACK);
                boots.setItemMeta(bootsMeta);
                boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                boots.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
                player.getInventory().setItem(0, sword);
                player.getInventory().setItem(8, pearl);
                player.getInventory().setHelmet(XMaterial.IRON_HELMET.parseItem());
                player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                player.getInventory().setBoots(boots);
                for (int i = 0; i < 3; i++) {
                    player.getInventory().addItem(XMaterial.GOLDEN_APPLE.parseItem());
                }
                Utils.sendMessage(player, "&7You have selected the &5Enderman &7kit.");
                plugin.endermanCooldown.put(player.getUniqueId(), 5400);
                plugin.endermanKit.add(player.getUniqueId());
            }
        }
    }
}
