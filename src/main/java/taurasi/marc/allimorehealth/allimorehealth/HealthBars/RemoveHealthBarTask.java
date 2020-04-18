package taurasi.marc.allimorehealth.allimorehealth.HealthBars;

import org.bukkit.scheduler.BukkitRunnable;

public class RemoveHealthBarTask extends BukkitRunnable {
    private HealthBar healthBar;

    public RemoveHealthBarTask(HealthBar healthBar){
        this.healthBar = healthBar;
    }

    @Override
    public void run() {
        healthBar.RemoveHealthBar();
    }
}
