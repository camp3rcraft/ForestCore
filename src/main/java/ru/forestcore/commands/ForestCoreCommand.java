package ru.forestcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.forestcore.ForestCore;
import ru.forestcore.menus.PluginMenu;
import ru.forestcore.models.ForestPlugin;
import ru.forestcore.utils.ColorUtils;

public class ForestCoreCommand implements CommandExecutor {
    private final ForestCore plugin;
    private final PluginMenu pluginMenu;

    public ForestCoreCommand(ForestCore plugin) {
        this.plugin = plugin;
        this.pluginMenu = new PluginMenu(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendInfo(sender, null);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "help":
                if (args.length > 1) {
                    sendPluginHelp(sender, args[1]);
                } else {
                    sendHelp(sender);
                }
                break;
            case "reload":
                if (args.length > 1) {
                    reloadPlugin(sender, args[1]);
                } else {
                    sender.sendMessage(ColorUtils.colorize("&cUsage: /fcore reload <plugin-id>"));
                }
                break;
            case "menu":
                if (sender instanceof Player) {
                    pluginMenu.openMenu((Player) sender);
                } else {
                    sender.sendMessage(ColorUtils.colorize("&cThis command can only be used by players!"));
                }
                break;
            default:
                if (args.length == 1) {
                    sendInfo(sender, args[0]);
                } else {
                    sendHelp(sender);
                }
                break;
        }

        return true;
    }

    private void sendInfo(CommandSender sender, String pluginId) {
        if (pluginId == null) {
            sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
            sender.sendMessage(ColorUtils.colorize("&#b1ed9d&lForestCore &7- Core plugin for camper_crafting plugins"));
            sender.sendMessage(ColorUtils.colorize("&7Version: &f" + plugin.getDescription().getVersion()));
            sender.sendMessage(ColorUtils.colorize("&7Author: &fcamper_crafting"));
            sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
        } else {
            ForestPlugin forestPlugin = plugin.getPluginManager().getPlugin(pluginId);
            if (forestPlugin == null) {
                sender.sendMessage(ColorUtils.colorize("&cPlugin not found!"));
                return;
            }

            sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
            sender.sendMessage(ColorUtils.colorize("&#b1ed9d&l" + forestPlugin.getName()));
            sender.sendMessage(ColorUtils.colorize("&7Version: &f" + forestPlugin.getVersion()));
            sender.sendMessage(ColorUtils.colorize("&7ID: &f" + forestPlugin.getId()));
            sender.sendMessage(ColorUtils.colorize("&7Author: &f" + forestPlugin.getAuthor()));
            sender.sendMessage(ColorUtils.colorize("&7Status: " + (forestPlugin.isEnabled() ? "&#a1ff2eEnabled" : "&#ff2e2eDisabled")));
            sender.sendMessage(ColorUtils.colorize("&7Modrinth: &f" + forestPlugin.getModrinthUrl()));
            sender.sendMessage(ColorUtils.colorize("&7GitHub: &f" + forestPlugin.getGithubUrl()));
            sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
        }
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
        sender.sendMessage(ColorUtils.colorize("&#b1ed9d&lForestCore Commands:"));
        sender.sendMessage(ColorUtils.colorize("&7/fcore &8- &fShow ForestCore information"));
        sender.sendMessage(ColorUtils.colorize("&7/fcore help &8- &fShow this help message"));
        sender.sendMessage(ColorUtils.colorize("&7/fcore help <plugin-id> &8- &fShow plugin commands"));
        sender.sendMessage(ColorUtils.colorize("&7/fcore reload <plugin-id> &8- &fReload plugin configuration"));
        sender.sendMessage(ColorUtils.colorize("&7/fcore menu &8- &fOpen plugin management menu"));
        sender.sendMessage(ColorUtils.colorize("&7/fcore <plugin-id> &8- &fShow plugin information"));
        sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
    }

    private void sendPluginHelp(CommandSender sender, String pluginId) {
        ForestPlugin forestPlugin = plugin.getPluginManager().getPlugin(pluginId);
        if (forestPlugin == null) {
            sender.sendMessage(ColorUtils.colorize("&cPlugin not found!"));
            return;
        }

        sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
        sender.sendMessage(ColorUtils.colorize("&#b1ed9d&l" + forestPlugin.getName() + " Commands:"));
        for (String cmd : forestPlugin.getCommands()) {
            sender.sendMessage(ColorUtils.colorize("&7" + cmd));
        }
        sender.sendMessage(ColorUtils.colorize("&8&m--------------------------------"));
    }

    private void reloadPlugin(CommandSender sender, String pluginId) {
        if (plugin.getPluginManager().reloadPluginConfig(pluginId)) {
            sender.sendMessage(ColorUtils.colorize("&#a1ff2ePlugin configuration reloaded successfully!"));
        } else {
            sender.sendMessage(ColorUtils.colorize("&#ff2e2eFailed to reload plugin configuration!"));
        }
    }
} 