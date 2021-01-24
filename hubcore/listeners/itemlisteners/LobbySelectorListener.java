package me.yochran.hubcore.listeners.itemlisteners;

import me.yochran.hubcore.HubCore;
import me.yochran.hubcore.Utils.Hub;
import me.yochran.hubcore.Utils.Utils;
import me.yochran.hubcore.Utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LobbySelectorListener implements Listener {

    private HubCore plugin;
    Hub hub = new Hub();

    public LobbySelectorListener() {
        plugin = HubCore.getPlugin(HubCore.class);
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (hub.isHubServer(player.getWorld().getName())) {
            if (player.getInventory().getItemInHand().getType() == XMaterial.CLOCK.parseMaterial()) {
                Inventory gui = Bukkit.createInventory(player, 27, Utils.translate("&e&lLobby Selector"));

                ItemStack filler = XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();

                for (int i = 0; i < 9; i++) {
                    gui.setItem(i, filler);
                }

                for (int i = 18; i < 27; i++) {
                    gui.setItem(i, filler);
                }

                ItemStack hub = XMaterial.CLOCK.parseItem();

                for (String server : plugin.getConfig().getStringList("HubServers")) {
                    ItemMeta hubMeta = hub.getItemMeta();

                    hubMeta.setDisplayName(Utils.translate("&e&l" + server));

                    List<String> lore = new ArrayList<>();
                    lore.add(Utils.translate("&7&m---------------------"));
                    lore.add(Utils.translate("&aClick this to go to &e" + server + "&a."));
                    lore.add(Utils.translate("&7&m---------------------"));

                    hubMeta.setLore(lore);

                    hub.setItemMeta(hubMeta);

                    gui.addItem(hub);
                }
                player.openInventory(gui);
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(Utils.translate("&e&lLobby Selector"))) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
                if (event.getCurrentItem().getType() == XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial()) {
                    event.setCancelled(true);
                } else {
                    String itemName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "send " + player.getName() + " " + itemName);
                }
            }
        }
    }
}
