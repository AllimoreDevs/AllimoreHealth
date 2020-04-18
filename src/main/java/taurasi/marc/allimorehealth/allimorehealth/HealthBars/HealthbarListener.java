package taurasi.marc.allimorehealth.allimorehealth.HealthBars;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import taurasi.marc.allimorehealth.allimorehealth.Allimorehealth;

public class HealthbarListener implements Listener {

    @EventHandler
    public void OnEntityDamage (EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(HealthBar.DoesNotDisplayHealthBar(event.getEntity())) return;
        Allimorehealth.HEALTHBARS.EnemyHealthChangeEvent(entity, event.getFinalDamage());
    }

    @EventHandler
    public void OnEntityRegainHealth (EntityRegainHealthEvent event){
        Entity entity = event.getEntity();

        if(HealthBar.DoesNotDisplayHealthBar(event.getEntity())) return;
        Allimorehealth.HEALTHBARS.EnemyHealthChangeEvent(entity, event.getAmount()*-1);
    }

    @EventHandler
    public void OnEntityDeath (EntityDeathEvent event){
        Entity entity = event.getEntity();

        if(HealthBar.DoesNotDisplayHealthBar(event.getEntity())) return;
        Allimorehealth.HEALTHBARS.EnemyDeathEvent(entity);
    }
}
