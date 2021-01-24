package me.yochran.hubcore.commands;

import me.yochran.hubcore.Utils.Console;
import me.yochran.hubcore.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Console.sendMessage("You must be a player.");
        } else {
            Player player = (Player) sender;
            Utils.liner(player);
            Utils.sendMessage(player, "&7[&6&lHub-Core&7]:");
            Utils.spacer(player);
            Utils.sendMessage(player, "&ePlugin By: &5&lYochran");
            Utils.sendMessage(player, "&ePlugin Version: &61.0");
            Utils.liner(player);
        }
        return true;
    }
}
