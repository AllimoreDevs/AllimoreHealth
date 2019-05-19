package taurasi.marc.allimorehealth.allimorehealth.ScalingHealth;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import taurasi.marc.allimorecore.CustomConfig;

import java.util.Objects;

public class ScalingHealthManager {
    // Default Value of starting health if the plugin is unable to read it from the config
    private int startingMaxHealth = 8;

    private CustomConfig configWrapper;

    public ScalingHealthManager(int startingMaxHealth, CustomConfig configWrapper){
        // Read Values from the config
        this.startingMaxHealth = startingMaxHealth;

        this.configWrapper = configWrapper;
    }

    void OnPlayerJoin(Player player){
        if(ContainsPlayer(player)){
            ReadPlayerData(player);
        }else{
            CreateNewPlayerDataEntry(player);
        }
    }

    void SetPlayerHealth(Player player, int amount){
        WritePlayerData(player, amount);
    }

    private void CreateNewPlayerDataEntry(Player player){
        WritePlayerData(player, startingMaxHealth);
    }

    private void WritePlayerData(Player player, int amount){
        SetPlayerMaxHealth(player, amount);
        configWrapper.GetConfig().set("Players." + player.getUniqueId() + ".maxHealth", amount);
        configWrapper.GetConfig().set("Players." + player.getUniqueId() + ".name", player.getDisplayName());
        configWrapper.SaveConfig();
    }

    private void ReadPlayerData(Player player){
        int maxHealth = configWrapper.GetConfig().getInt("Players." + player.getUniqueId() + ".maxHealth");
        SetPlayerMaxHealth(player, maxHealth);
    }

    private boolean ContainsPlayer(Player player){
        return configWrapper.GetConfig().contains("Players." + player.getUniqueId());
    }

    private void SetPlayerMaxHealth(Player player, int amount){
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(amount);
    }
}
