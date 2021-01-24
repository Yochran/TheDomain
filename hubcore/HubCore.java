package me.yochran.hubcore;

import me.yochran.hubcore.Utils.Console;
import me.yochran.hubcore.commands.HubCoreCommand;
import me.yochran.hubcore.listeners.ItemDropListener;
import me.yochran.hubcore.listeners.PlayerJoinListener;
import me.yochran.hubcore.listeners.WorldChangeListener;
import me.yochran.hubcore.listeners.itemlisteners.LobbySelectorListener;
import me.yochran.hubcore.listeners.itemlisteners.ServerSelectorListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HubCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        startupAnnouncements();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    void startupAnnouncements() {
        Console.sendMessage("&7[&6&lHub&7] &eHub core v1.0 by Yochran is loading...");
        Console.sendMessage("&7[&6&lHub&7] &eHub core v1.0 by Yochran has successfully loaded.");
    }

    void shutdownAnnouncements() {
        Console.sendMessage("&7[&6&lHub&7] &eHub core v1.0 by Yochran is unloading...");
        Console.sendMessage("&7[&6&lHub&7] &eHub core v1.0 by Yochran has successfully unloaded.");
    }

    protected void registerCommands() {
        getCommand("Hubcore").setExecutor(new HubCoreCommand());
    }

    protected void registerListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerJoinListener(), this);
        manager.registerEvents(new WorldChangeListener(), this);
        manager.registerEvents(new LobbySelectorListener(), this);
        manager.registerEvents(new ServerSelectorListener(), this);
        manager.registerEvents(new ItemDropListener(), this);
    }
}
