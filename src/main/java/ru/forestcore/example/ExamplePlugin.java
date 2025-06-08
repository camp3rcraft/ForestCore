package ru.forestcore.example;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.forestcore.ForestCore;
import ru.forestcore.models.ForestPlugin;
import ru.forestcore.utils.ColorUtils;

import java.util.Arrays;

public class ExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Регистрация плагина в ForestCore
        ForestCore forestCore = (ForestCore) Bukkit.getPluginManager().getPlugin("ForestCore");
        if (forestCore != null) {
            ForestPlugin forestPlugin = new ForestPlugin(
                "Example Plugin",
                getDescription().getVersion(),
                "example-plugin",
                "Forest",
                "https://modrinth.com/plugin/example-plugin",
                "https://github.com/your-username/example-plugin",
                Arrays.asList(
                    "/example - Показать пример цветов",
                    "/example hex - Показать пример HEX цветов"
                )
            );
            forestCore.getPluginManager().registerPlugin("example-plugin", forestPlugin);
        }

        // Регистрация команды
        getCommand("example").setExecutor(new ExampleCommand());
    }

    private class ExampleCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (args.length > 0 && args[0].equalsIgnoreCase("hex")) {
                // Пример HEX цветов
                sender.sendMessage(ColorUtils.colorize("&#FF0000Красный текст"));
                sender.sendMessage(ColorUtils.colorize("&#00FF00Зеленый текст"));
                sender.sendMessage(ColorUtils.colorize("&#0000FFСиний текст"));
                sender.sendMessage(ColorUtils.colorize("&#FF00FFРозовый текст"));
                sender.sendMessage(ColorUtils.colorize("&#FFFF00Желтый текст"));
                sender.sendMessage(ColorUtils.colorize("&#00FFFFГолубой текст"));
            } else {
                // Пример ванильных цветов
                sender.sendMessage(ColorUtils.colorize("&cКрасный текст"));
                sender.sendMessage(ColorUtils.colorize("&aЗеленый текст"));
                sender.sendMessage(ColorUtils.colorize("&9Синий текст"));
                sender.sendMessage(ColorUtils.colorize("&dРозовый текст"));
                sender.sendMessage(ColorUtils.colorize("&eЖелтый текст"));
                sender.sendMessage(ColorUtils.colorize("&bГолубой текст"));
            }
            return true;
        }
    }
} 