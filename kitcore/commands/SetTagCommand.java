package me.yochran.kitcore.commands;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.Console;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.Utils;
import net.vectromc.vnitrogen.vNitrogen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTagCommand implements CommandExecutor {

    private KitCore plugin;
    private vNitrogen nitrogen;

    public SetTagCommand() {
        plugin = KitCore.getPlugin(KitCore.class);
        nitrogen = vNitrogen.getPlugin(vNitrogen.class);
    }

    KitPvP kitpvp = new KitPvP();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Console.sendMessage("&7[&6&lKit-Core&7] &cYou must be a player to use that command.");
        } else {
            Player player = (Player) sender;
            if (!player.hasPermission("kitcore.settag")) {
                Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cYou do not have permission to use that.");
            } else{
                if (!KitPvP.isKitpvpServer(player.getWorld())) {
                    Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cYou cannot use that command here.");
                } else {
                    if (args.length != 1) {
                        Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cIncorrect Usage! /settag <player>");
                    } else {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            kitpvp.addCombat(target);
                            plugin.combatDuo.put(target.getUniqueId(), player.getUniqueId());
                            nitrogen.setPlayerColor(target);
                            Utils.sendMessage(player, "&7You have put &b" + target.getDisplayName() + "&7 into combat.");
                        } else {
                            Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cInvalid Player!");
                        }
                    }
                }
            }
        }
        return true;
    }
}
