package me.yochran.kitcore;

import me.yochran.kitcore.commands.KitCommand;
import me.yochran.kitcore.commands.KitshopCommand;
import me.yochran.kitcore.data.PlayerData;
import me.yochran.kitcore.listeners.*;
import me.yochran.kitcore.listeners.abilities.EndermanAbility;
import me.yochran.kitcore.listeners.abilities.specialitems.WindStaffAbility;
import me.yochran.kitcore.runnables.KitCooldowns;
import me.yochran.kitcore.runnables.PearlCooldown;
import me.yochran.kitcore.runnables.WindStaffCooldown;
import me.yochran.kitcore.utils.Console;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class KitCore extends JavaPlugin {

    public PlayerData data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerData();
        registerCommands();
        registerListeners();
        runRunnables();
        startupAnnouncements();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        shutdownAnnouncements();
    }

    public HashMap<UUID, Integer> windstaffCooldown = new HashMap<>();

    public HashMap<UUID, Integer> pvpCooldown = new HashMap<>();
    public HashMap<UUID, Integer> fishermanCooldown = new HashMap<>();
    public HashMap<UUID, Integer> endermanCooldown = new HashMap<>();
    public HashMap<UUID, Integer> pearlCooldown = new HashMap<>();

    public ArrayList<UUID> endermanKit = new ArrayList<>();

    void startupAnnouncements() {
        Console.sendMessage("&7[&6&lKitCore&7] &eKit-Core v1.0 by Yochran is loading...");
        Console.sendMessage("&7[&6&lKitCore&7] &eKit-Core v1.0 by Yochran has successfully loaded.");
    }

    void shutdownAnnouncements() {
        Console.sendMessage("&7[&6&lKitCore&7] &eKit-Core v1.0 by Yochran is unloading...");
        Console.sendMessage("&7[&6&lKitCore&7] &eKit-Core v1.0 by Yochran has successfully unloaded.");
    }

    protected void registerCommands() {
        getCommand("Kit").setExecutor(new KitCommand());
        getCommand("KitShop").setExecutor(new KitshopCommand());
    }

    protected void registerListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new GuiClickListeners(), this);
        manager.registerEvents(new PlayerDeathListener(), this);
        manager.registerEvents(new HungerListener(), this);
        manager.registerEvents(new EndermanAbility(), this);
        manager.registerEvents(new EnderpearlThrowListener(), this);
        manager.registerEvents(new PlayerJoinListener(), this);
        manager.registerEvents(new WindStaffAbility(), this);
    }

    protected void runRunnables() {
        new KitCooldowns().runTaskTimer(this, 0, 20);
        new PearlCooldown().runTaskTimer(this, 0, 20);
        new WindStaffCooldown().runTaskTimer(this, 0, 20);
    }

    protected void registerData() {
        data = new PlayerData();
        data.setupData();
        data.saveData();
        data.reloadData();
    }
}
