package me.yochran.kitcore.commands;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.Console;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.Utils;
import me.yochran.kitcore.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KitCommand implements CommandExecutor {

    private KitCore plugin;

    public KitCommand() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Console.sendMessage("&7[&6&lKit-Core&7] &cYou must be a player to use that command.");
        } else {
            Player player = (Player) sender;
            if (!KitPvP.isKitpvpServer(player.getWorld())) {
                Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cYou cannot use that command here.");
            } else {
                Inventory gui = Bukkit.createInventory(player, 36, Utils.translate("&6&lKit Selector"));

                ItemStack filler = XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();

                for (int i = 0; i < 9; i++) {
                    gui.setItem(i, filler);
                }
                for (int i = 27; i < 36; i++) {
                    gui.setItem(i, filler);
                }

                ItemStack comingSoon = XMaterial.BEDROCK.parseItem();
                ItemMeta comingSoonMeta = comingSoon.getItemMeta();
                comingSoonMeta.setDisplayName(Utils.translate("&c&lCOMING SOON!"));
                List<String> comingSoonLore = new ArrayList<>();
                comingSoonLore.add(Utils.translate("&7This kit will be added"));
                comingSoonLore.add(Utils.translate("&7in the near future."));
                comingSoonMeta.setLore(comingSoonLore);
                comingSoon.setItemMeta(comingSoonMeta);

                for (int i = 10; i < 17; i++) {
                    gui.setItem(i, comingSoon);
                }

                for (int i = 20; i < 25; i++) {
                    gui.setItem(i, comingSoon);
                }

                List<String> warning = new ArrayList<>();
                warning.add(Utils.translate("&c&lWARNING: &7Selecting this kit"));
                warning.add(Utils.translate("&7clear your current inventory."));

                ItemStack pvp = XMaterial.DIAMOND_SWORD.parseItem();
                ItemMeta pvpMeta = pvp.getItemMeta();
                pvpMeta.setDisplayName(Utils.translate("&aDefault &7(&2Owned&7)"));
                pvpMeta.setLore(warning);
                pvp.setItemMeta(pvpMeta);

                gui.setItem(10, pvp);

                ItemStack fisherman = XMaterial.FISHING_ROD.parseItem();
                ItemMeta fishermanMeta = fisherman.getItemMeta();
                if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Fisherman")) {
                    fishermanMeta.setDisplayName(Utils.translate("&bFisherman &7(&4Not Owned&7)"));
                } else {
                    fishermanMeta.setDisplayName(Utils.translate("&bFisherman &7(&2Owned&7)"));
                }
                fishermanMeta.setLore(warning);
                fisherman.setItemMeta(fishermanMeta);

                gui.setItem(13, fisherman);

                ItemStack enderman = XMaterial.ENDER_PEARL.parseItem();
                ItemMeta endermanMeta = enderman.getItemMeta();
                if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Enderman")) {
                    endermanMeta.setDisplayName(Utils.translate("&5Enderman &7(&4Not Owned&7)"));
                } else {
                    endermanMeta.setDisplayName(Utils.translate("&5Enderman &7(&2Owned&7)"));
                }
                endermanMeta.setLore(warning);
                enderman.setItemMeta(endermanMeta);

                gui.setItem(14, enderman);

                ItemStack archer = XMaterial.BOW.parseItem();
                ItemMeta archerMeta = archer.getItemMeta();
                archerMeta.setDisplayName(Utils.translate("&dArcher &7(&2Owned&7)"));
                archerMeta.setLore(warning);
                archer.setItemMeta(archerMeta);

                gui.setItem(11, archer);

                ItemStack rogue = XMaterial.SUGAR.parseItem();
                ItemMeta rogueMeta = rogue.getItemMeta();
                if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Rogue")) {
                    rogueMeta.setDisplayName(Utils.translate("&8Rogue &7(&4Not Owned&7)"));
                } else {
                    rogueMeta.setDisplayName(Utils.translate("&8Rogue &7(&2Owned&7)"));
                }
                rogueMeta.setLore(warning);
                rogue.setItemMeta(rogueMeta);

                gui.setItem(12, rogue);

                player.openInventory(gui);
            }
        }
        return true;
    }
}
