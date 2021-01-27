package me.yochran.kitcore.commands;

import me.yochran.kitcore.KitCore;
import me.yochran.kitcore.utils.Console;
import me.yochran.kitcore.utils.KitPvP;
import me.yochran.kitcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatTagCommand implements CommandExecutor {

    private KitCore plugin;

    public CombatTagCommand() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    KitPvP kitpvp = new KitPvP();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Console.sendMessage("&7[&6&lKit-Core&7] &cYou must be a player to use that command.");
        } else {
            Player player = (Player) sender;
            if (!KitPvP.isKitpvpServer(player.getWorld())) {
                Utils.sendMessage(player, "&7[&6&lKitPvP&7] &cYou cannot use that command here.");
            } else {
                if (!kitpvp.isInCombat(player)) {
                    Utils.sendMessage(player, "&7You are not in combat.");
                } else {
                    Utils.sendMessage(player, "&7You are in combat for &b" + kitpvp.getCombatTime(player) + "&7 more seconds.");
                }
            }
        }
        return true;
    }
}
