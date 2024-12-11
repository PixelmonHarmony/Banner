package tech.sethi.pebbles.forgevaultbridge;

import com.mojang.logging.LogUtils;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.slf4j.Logger;

import java.util.UUID;

public class Forgevaultbridge {
    private static Economy economy;
    private static Permission permission;
    private static Chat chat;
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void onServerStarting() {
        LOGGER.info("Server starting");
        LOGGER.info("Initializing Pebble's Fabric Vault Bridge");
        setupEconomy();
    }

    private static void setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            LOGGER.info("Cannot find Vault!");
        } else {
            RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp == null) {
                LOGGER.info("Registered Service Provider for Economy.class not found");
            } else {
                economy = (Economy)rsp.getProvider();
                LOGGER.info("Economy successfully hooked up");
                LOGGER.info("Economy: " + economy.getName());
            }
        }
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static String getPlayerName(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }
}
