package ru.forestcore.managers;

import org.bukkit.Bukkit;
import ru.forestcore.ForestCore;
import ru.forestcore.models.ForestPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {
    private final ForestCore plugin;
    private final int resourceId;

    public UpdateChecker(ForestCore plugin) {
        this.plugin = plugin;
        this.resourceId = 0; // Replace with your Modrinth resource ID
    }

    public void startChecking() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this::checkUpdates, 0L, 72000L); // Check every hour
    }

    private void checkUpdates() {
        for (ForestPlugin forestPlugin : plugin.getPluginManager().getRegisteredPlugins().values()) {
            String latestVersion = getLatestVersion(forestPlugin.getModrinthUrl());
            if (latestVersion != null && !latestVersion.equals(forestPlugin.getVersion())) {
                Bukkit.getConsoleSender().sendMessage(
                    "§a[ForestCore] Update available for " + forestPlugin.getName() + 
                    ": " + forestPlugin.getVersion() + " -> " + latestVersion
                );
            }
        }
    }

    private String getLatestVersion(String modrinthUrl) {
        try {
            URL url = new URL(modrinthUrl + "/versions");
            InputStream inputStream = url.openStream();
            Scanner scanner = new Scanner(inputStream);
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();
            
            // Parse the response to get the latest version
            // This is a simplified version, you'll need to implement proper JSON parsing
            return response.split("\"version_number\":\"")[1].split("\"")[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
} 