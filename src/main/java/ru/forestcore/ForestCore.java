package ru.forestcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.forestcore.commands.ForestCoreCommand;
import ru.forestcore.managers.PluginManager;
import ru.forestcore.managers.UpdateChecker;
import ru.forestcore.utils.ColorUtils;

public class ForestCore extends JavaPlugin {
    private static ForestCore instance;
    private PluginManager pluginManager;
    private UpdateChecker updateChecker;

    @Override
    public void onEnable() {
        instance = this;
        
        // Initialize managers
        this.pluginManager = new PluginManager(this);
        this.updateChecker = new UpdateChecker(this);
        
        // Register commands
        getCommand("fcore").setExecutor(new ForestCoreCommand(this));
        
        // Start update checker
        updateChecker.startChecking();
        
        getLogger().info("ForestCore has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ForestCore has been disabled!");
    }

    public static ForestCore getInstance() {
        return instance;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
} 