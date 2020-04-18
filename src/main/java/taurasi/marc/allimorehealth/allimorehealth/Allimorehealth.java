package taurasi.marc.allimorehealth.allimorehealth;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import taurasi.marc.allimorehealth.allimorehealth.HealthBars.HealthbarListener;
import taurasi.marc.allimorehealth.allimorehealth.HealthBars.HealthbarsManager;
import taurasi.marc.allimorehealth.allimorehealth.ScalingHealth.CommandManager;
import taurasi.marc.allimorehealth.allimorehealth.ScalingHealth.ConfigWrapper;
import taurasi.marc.allimorehealth.allimorehealth.ScalingHealth.ConnectListener;
import taurasi.marc.allimorehealth.allimorehealth.ScalingHealth.ScalingHealthManager;

public final class Allimorehealth extends JavaPlugin {
    // Enemies always display Health Bars, Experimental Feature
    public static boolean ALWAYS_SHOW_HEALTHBAR = false;

    public static Allimorehealth INSTANCE;
    public static HealthbarsManager HEALTHBARS;

    private ScalingHealthManager scalingHealthManager;

    private HealthbarListener damageListener;
    private ConnectListener connectListener;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        // Singletons
        ConfigWrapper configWrapper = new ConfigWrapper(this);
        scalingHealthManager = new ScalingHealthManager(configWrapper.getStartingHealth(), configWrapper.getPlayerData());
        HEALTHBARS = new HealthbarsManager();

        // Set up Listeners
        damageListener = new HealthbarListener();
        connectListener = new ConnectListener(scalingHealthManager);

        getServer().getPluginManager().registerEvents(damageListener, this);
        getServer().getPluginManager().registerEvents(connectListener, this);

        // Set up Command
        this.getCommand("SetMaxHealth").setExecutor(new CommandManager());
        this.getCommand("DebugTod").setExecutor(new CommandManager());

        getServer().getConsoleSender().sendMessage("[AllimoreHealth!] Allimore Health plugin Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("[AllimoreHealth] Allimore Health plugin Disabled!");

        HandlerList.unregisterAll(damageListener);
        HandlerList.unregisterAll(connectListener);
    }

    public ScalingHealthManager GetScalingHealthManager(){
        return scalingHealthManager;
    }
}
