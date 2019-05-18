package taurasi.marc.allimorehealth.allimorehealth.ScalingHealth;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectListener implements Listener {
    private ScalingHealthManager scalingHealthManager;

    public ConnectListener (ScalingHealthManager scalingHealthManager){
        this.scalingHealthManager = scalingHealthManager;
    }

    @EventHandler
    public void OnPlayerJoinEvent(PlayerJoinEvent event){
        scalingHealthManager.OnPlayerJoin(event.getPlayer());
    }
}
