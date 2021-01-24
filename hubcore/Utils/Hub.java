package me.yochran.hubcore.Utils;

import me.yochran.hubcore.HubCore;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hub {

    private HubCore plugin;

    public Hub() {
        plugin = HubCore.getPlugin(HubCore.class);
    }

    public static void giveItems(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setHelmet(XMaterial.AIR.parseItem());
        player.getInventory().setChestplate(XMaterial.AIR.parseItem());
        player.getInventory().setLeggings(XMaterial.AIR.parseItem());
        player.getInventory().setBoots(XMaterial.AIR.parseItem());

        ItemStack serverSelector = XMaterial.COMPASS.parseItem();
        ItemStack lobbySelector = XMaterial.CLOCK.parseItem();

        ItemMeta serverSelectorMeta = serverSelector.getItemMeta();
        ItemMeta lobbySelectorMeta = lobbySelector.getItemMeta();

        serverSelectorMeta.setDisplayName(Utils.translate("&6&lServer Selector"));
        lobbySelectorMeta.setDisplayName(Utils.translate("&e&lHub Selector"));

        serverSelector.setItemMeta(serverSelectorMeta);
        lobbySelector.setItemMeta(lobbySelectorMeta);

        player.getInventory().setItem(4, serverSelector);
        player.getInventory().setItem(8, lobbySelector);
    }

    public boolean isHubServer(String name) {
        if (plugin.getConfig().getStringList("HubServers").contains(name)) {
            return true;
        } else {
            return false;
        }
    }
}
