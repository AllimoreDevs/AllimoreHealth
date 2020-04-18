package taurasi.marc.allimorehealth.allimorehealth.ScalingHealth;

import org.bukkit.plugin.Plugin;
import taurasi.marc.allimorecore.CustomConfig;

public class ConfigWrapper {
    private CustomConfig playerData;
    private int startingHealth;

    public ConfigWrapper(Plugin plugin) {
        // Set up Config
        plugin.saveDefaultConfig();

        playerData = new CustomConfig("PlayerData.yml", plugin.getDataFolder().getPath(), plugin);

        ReadNormalValues(plugin);
    }

    private void ReadNormalValues(Plugin plugin) {
        startingHealth = plugin.getConfig().getInt("StartingHealth");
    }

    public CustomConfig getPlayerData() {
        return playerData;
    }

    public int getStartingHealth() {
        return startingHealth;
    }
}

