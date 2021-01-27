package me.yochran.kitcore.commands;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.Console;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.Utils;
import me.yochran.kitcore.utils.XMaterial;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class ClearKitCommand implements CommandExecutor {

    private KitCore plugin;

    public ClearKitCommand() {
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
                player.getInventory().clear();
                player.getInventory().setHelmet(XMaterial.AIR.parseItem());
                player.getInventory().setChestplate(XMaterial.AIR.parseItem());
                player.getInventory().setLeggings(XMaterial.AIR.parseItem());
                player.getInventory().setBoots(XMaterial.AIR.parseItem());

                plugin.endermanKit.remove(player.getUniqueId());

                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }

                Utils.sendMessage(player, "&aYou have cleared your kit.");
            }
        }
        return true;
    }
}
