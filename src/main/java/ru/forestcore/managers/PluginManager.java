package ru.forestcore.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ru.forestcore.ForestCore;
import ru.forestcore.models.ForestPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PluginManager {
    private final ForestCore plugin;
    private final Map<String, ForestPlugin> registeredPlugins;

    public PluginManager(ForestCore plugin) {
        this.plugin = plugin;
        this.registeredPlugins = new HashMap<>();
    }

    public void registerPlugin(String pluginId, ForestPlugin forestPlugin) {
        registeredPlugins.put(pluginId, forestPlugin);
    }

    public void unregisterPlugin(String pluginId) {
        registeredPlugins.remove(pluginId);
    }

    public ForestPlugin getPlugin(String pluginId) {
        return registeredPlugins.get(pluginId);
    }

    public Map<String, ForestPlugin> getRegisteredPlugins() {
        return registeredPlugins;
    }

    public boolean reloadPluginConfig(String pluginId) {
        ForestPlugin forestPlugin = getPlugin(pluginId);
        if (forestPlugin == null) return false;

        Plugin bukkitPlugin = plugin.getServer().getPluginManager().getPlugin(pluginId);
        if (bukkitPlugin == null) return false;

        try {
            File configFile = new File(bukkitPlugin.getDataFolder(), "config.yml");
            if (!configFile.exists()) return false;

            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            bukkitPlugin.getConfig().setDefaults(config);
            bukkitPlugin.getConfig().options().copyDefaults(true);
            bukkitPlugin.saveConfig();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 