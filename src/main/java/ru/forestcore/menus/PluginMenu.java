package ru.forestcore.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.forestcore.ForestCore;
import ru.forestcore.models.ForestPlugin;
import ru.forestcore.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class PluginMenu {
    private final ForestCore plugin;
    private final Inventory inventory;

    public PluginMenu(ForestCore plugin) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(null, 54, ColorUtils.colorize("&8ForestCore Plugins"));
        updateMenu();
    }

    public void openMenu(Player player) {
        updateMenu();
        player.openInventory(inventory);
    }

    private void updateMenu() {
        inventory.clear();
        int slot = 0;

        for (ForestPlugin forestPlugin : plugin.getPluginManager().getRegisteredPlugins().values()) {
            if (slot >= 54) break;

            ItemStack item = new ItemStack(Material.BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ColorUtils.colorize("&e" + forestPlugin.getName()));

            List<String> lore = new ArrayList<>();
            lore.add(ColorUtils.colorize("&7Version: &f" + forestPlugin.getVersion()));
            lore.add(ColorUtils.colorize("&7ID: &f" + forestPlugin.getId()));
            lore.add(ColorUtils.colorize("&7Status: " + (forestPlugin.isEnabled() ? "&aEnabled" : "&cDisabled")));
            lore.add("");
            lore.add(ColorUtils.colorize("&7Click to edit configuration"));

            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.setItem(slot++, item);
        }
    }

    public void handleClick(Player player, int slot) {
        if (slot >= plugin.getPluginManager().getRegisteredPlugins().size()) return;

        ForestPlugin forestPlugin = new ArrayList<>(plugin.getPluginManager().getRegisteredPlugins().values()).get(slot);
        // Open configuration editor for the selected plugin
        // This will be implemented in a separate class
    }
} 