package me.yochran.kitcore.data;

import me.yochran.kitcore.KitCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    private KitCore plugin;
    public File file;
    public FileConfiguration config;

    public PlayerData() {
        plugin = KitCore.getPlugin(KitCore.class);
    }

    public void setupData() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("[KitCore] data.yml file could not load.");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getData() {
        return config;
    }

    public void saveData() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[VectroMC] data.yml file could not save.");
        }
    }

    public void reloadData() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
