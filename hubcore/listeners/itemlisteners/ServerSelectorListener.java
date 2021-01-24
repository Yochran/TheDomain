package me.yochran.hubcore.listeners.itemlisteners;

import me.yochran.hubcore.HubCore;
import me.yochran.hubcore.Utils.Hub;
import me.yochran.hubcore.Utils.Utils;
import me.yochran.hubcore.Utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServerSelectorListener implements Listener {

    private HubCore plugin;
    Hub hub = new Hub();

    public ServerSelectorListener() {
        plugin = HubCore.getPlugin(HubCore.class);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (hub.isHubServer(player.getWorld().getName())) {
            if (player.getInventory().getItemInHand().getType() == XMaterial.COMPASS.parseMaterial()) {
                Inventory gui = Bukkit.createInventory(player, 27, Utils.translate("&6&lServer Selector"));

                ItemStack filler = XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();

                for (int i = 0; i < 9; i++) {
                    gui.setItem(i, filler);
                }

                for (int i = 18; i < 27; i++) {
                    gui.setItem(i, filler);
                }

                ItemStack kitpvp = XMaterial.DIAMOND_SWORD.parseItem();
                ItemMeta kitpvpMeta = kitpvp.getItemMeta();
                kitpvpMeta.setDisplayName(Utils.translate("&c&lKitPvP"));
                List<String> kitLore = new ArrayList<>();
                kitLore.add(Utils.translate("&7Connect to the &cKitPvP &7server."));
                kitLore.add(Utils.translate("&7Fight and defeat your enemies."));
                kitpvpMeta.setLore(kitLore);
                kitpvp.setItemMeta(kitpvpMeta);

                ItemStack practice = new ItemStack(Material.POTION, 1, (short) 16421);
                ItemMeta practiceMeta = practice.getItemMeta();
                practiceMeta.setDisplayName(Utils.translate("&a&lPractice"));
                List<String> pracLore = new ArrayList<>();
                pracLore.add(Utils.translate("&7Connect to the &aPractice &7server."));
                pracLore.add(Utils.translate("&7Practice your PvP skills. &4COMING SOON!"));
                practiceMeta.setLore(pracLore);
                practice.setItemMeta(practiceMeta);

                gui.setItem(12, kitpvp);
                gui.setItem(14, practice);

                player.openInventory(gui);
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(Utils.translate("&6&lServer Selector"))) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
                if (event.getCurrentItem().getType() == XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial()) {
                    event.setCancelled(true);
                } else {
                    String itemName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "send " + player.getName() + " " + itemName.toLowerCase());
                }
            }
        }
    }
}
