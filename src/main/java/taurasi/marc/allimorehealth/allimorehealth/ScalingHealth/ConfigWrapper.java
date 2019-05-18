package taurasi.marc.allimorehealth.allimorehealth.ScalingHealth;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import taurasi.marc.allimorehealth.allimorehealth.Allimorehealth;

import java.io.File;
import java.io.IOException;

class ConfigWrapper {
    private String DataFileName = "PlayerData.yml";

    private File dataFile;
    private FileConfiguration dataConfig;

    public ConfigWrapper(){
        CreateDataFile();
    }

    private void CreateDataFile() {
        GetDataFile();
        GetConfigFromFile();
    }

    public void SaveConfig(){
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void GetDataFile(){
        dataFile = new File(Allimorehealth.INSTANCE.getDataFolder(), DataFileName);
        if(!dataFile.exists()){
            dataFile.getParentFile().mkdirs();
            Allimorehealth.INSTANCE.saveResource(DataFileName, false);
        }
    }

    private void GetConfigFromFile(){
        dataConfig = new YamlConfiguration();
        try {
            dataConfig.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return dataConfig;
    }
}
