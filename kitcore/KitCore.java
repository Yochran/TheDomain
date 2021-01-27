package me.yochran.kitcore;

import me.yochran.kitcore.commands.*;
import me.yochran.kitcore.data.PlayerData;
import me.yochran.kitcore.listeners.*;
import me.yochran.kitcore.listeners.abilities.EndermanAbility;
import me.yochran.kitcore.listeners.abilities.specialitems.WindStaffAbility;
import me.yochran.kitcore.runnables.CombatLogRunnable;
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
        saveDefaultConfig();
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
    public HashMap<UUID, Integer> archerCooldown = new HashMap<>();
    public HashMap<UUID, Integer> rogueCooldown = new HashMap<>();

    public HashMap<UUID, Integer> pearlCooldown = new HashMap<>();

    public HashMap<UUID, Integer> combat = new HashMap<>();
    public HashMap<UUID, UUID> combatDuo = new HashMap<>();
    public ArrayList<UUID> combatLogged = new ArrayList<>();

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
        getCommand("ClearKit").setExecutor(new ClearKitCommand());
        getCommand("CombatTag").setExecutor(new CombatTagCommand());
        getCommand("Untag").setExecutor(new UntagCommand());
        getCommand("Settag").setExecutor(new SetTagCommand());
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
        manager.registerEvents(new PlayerAttackListener(), this);
        manager.registerEvents(new PlayerLogoutListener(), this);
        manager.registerEvents(new WorldChangeListener(), this);
        manager.registerEvents(new CommandListener(), this);
    }

    protected void runRunnables() {
        new KitCooldowns().runTaskTimer(this, 0, 20);
        new PearlCooldown().runTaskTimer(this, 0, 20);
        new WindStaffCooldown().runTaskTimer(this, 0, 20);
        new CombatLogRunnable().runTaskTimer(this, 0, 20);
    }

    protected void registerData() {
        data = new PlayerData();
        data.setupData();
        data.saveData();
        data.reloadData();
    }
}
