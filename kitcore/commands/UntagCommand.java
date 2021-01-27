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

public class UntagCommand implements CommandExecutor {

    private KitCore plugin;
    private vNitrogen nitrogen;

    public UntagCommand() {
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
            if (!player.hasPermission("kitcore.untag")) {
                Utils.sendMessage(player, "&7[&6&lKitPvP] &cYou do not have permission to use that command.");
            } else {
                if (!KitPvP.isKitpvpServer(player.getWorld())) {
                    Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cYou cannot use that command here.");
                } else {
                    if (args.length != 1) {
                        Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cIncorrect Usage! /untag <player>");
                    } else {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            kitpvp.removeCombat(target);
                            nitrogen.setPlayerColor(target);
                            Utils.sendMessage(player, "&7You have removed " + target.getDisplayName() + "&7's combat tag.");
                            Utils.sendMessage(target, "&7You are no longer in combat.");
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
