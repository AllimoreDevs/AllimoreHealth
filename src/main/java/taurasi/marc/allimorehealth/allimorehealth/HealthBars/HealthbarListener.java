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
        if(HealthbarsManager.DoesNotDisplayHealthBar(event.getEntity())) return;
        HealthbarsManager.EnemyHealthChangeEvent(entity, event.getFinalDamage());
    }

    @EventHandler
    public void OnEntityRegainHealth (EntityRegainHealthEvent event){
        Entity entity = event.getEntity();

        if(HealthbarsManager.DoesNotDisplayHealthBar(event.getEntity())) return;
        HealthbarsManager.EnemyHealthChangeEvent(entity, event.getAmount()*-1);
    }

    @EventHandler
    public void OnEntityDeath (EntityDeathEvent event){
        Entity entity = event.getEntity();

        if(HealthbarsManager.DoesNotDisplayHealthBar(event.getEntity())) return;
        HealthbarsManager.EnemyDeathEvent(entity);
    }

    @EventHandler
    public void OnCreatureSpawn (CreatureSpawnEvent event){
        Entity entity = event.getEntity();

        // Ensures Entities that can have Health Bars spawn with them
        if(Allimorehealth.ALWAYS_SHOW_HEALTHBAR){
            if(HealthbarsManager.DoesNotDisplayHealthBar(event.getEntity())) return;
            HealthbarsManager.NewEnemyEvent(entity);
            return;
        }

        // Fix that stops slimes from spawning with empty health bars, only needed if health bars are not always visible
        if( event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT){
            HealthbarsManager.NewEnemyEvent(entity);
        }
    }


}
