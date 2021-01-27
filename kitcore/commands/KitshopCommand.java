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

public class KitshopCommand implements CommandExecutor {

    private KitCore plugin;

    public KitshopCommand() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Console.sendMessage("&7[&6&lKit-Core&7] &cYou must be a player to use that command.");
        } else {
            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 18, Utils.translate("&a&lKit Shop"));

            ItemStack filler = XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();

            for (int i = 0; i < 18; i++) {
                gui.setItem(i, filler);
            }

            ItemStack rogue = XMaterial.SUGAR.parseItem();
            ItemMeta rogueMeta = rogue.getItemMeta();
            if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Rogue")) {
                rogueMeta.setDisplayName(Utils.translate("&8Rogue &7($3500)"));
            } else {
                rogueMeta.setDisplayName(Utils.translate("&8Rogue &7(&2Owned&7)"));
            }
            List<String> rogueLore = new ArrayList<>();
            rogueLore.add(Utils.translate("&7Get permanent speed 2"));
            rogueLore.add(Utils.translate("&7and chain armor."));
            rogueMeta.setLore(rogueLore);
            rogue.setItemMeta(rogueMeta);

            gui.setItem(0, rogue);

            ItemStack fisherman = XMaterial.FISHING_ROD.parseItem();
            ItemMeta fishermanMeta = fisherman.getItemMeta();
            if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Fisherman")) {
                fishermanMeta.setDisplayName(Utils.translate("&bFisherman &7($5000)"));
            } else {
                fishermanMeta.setDisplayName(Utils.translate("&bFisherman &7(&2Owned&7)"));
            }
            List<String> fishermanLore = new ArrayList<>();
            fishermanLore.add(Utils.translate("&7Get an unbreaking 3 fishing rod"));
            fishermanLore.add(Utils.translate("&7to assist in your PvP."));
            fishermanMeta.setLore(fishermanLore);
            fisherman.setItemMeta(fishermanMeta);

            gui.setItem(1, fisherman);

            ItemStack enderman = XMaterial.ENDER_PEARL.parseItem();
            ItemMeta endermanMeta = enderman.getItemMeta();
            if (!plugin.data.config.getBoolean(player.getUniqueId().toString() + ".Enderman")) {
                endermanMeta.setDisplayName(Utils.translate("&5Enderman &7($6500)"));
            } else {
                endermanMeta.setDisplayName(Utils.translate("&5Enderman &7(&2Owned&7)"));
            }
            List<String> endermenLore = new ArrayList<>();
            endermenLore.add(Utils.translate("&7Get an infinite pearl that"));
            endermenLore.add(Utils.translate("&7regenerates after every throw.."));
            endermanMeta.setLore(endermenLore);
            enderman.setItemMeta(endermanMeta);

            gui.setItem(2, enderman);

            player.openInventory(gui);
        }
        return true;
    }
}
