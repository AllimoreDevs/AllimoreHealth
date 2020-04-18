package taurasi.marc.allimorehealth.allimorehealth.HealthBars;

import org.bukkit.entity.Entity;

import java.util.HashMap;

public class HealthbarsManager {
    private HashMap<Entity, HealthBar> healthBars;

    public HealthbarsManager(){
        healthBars = new HashMap<>();
    }

    void EnemyHealthChangeEvent(Entity entity, double amount){
        entity.setCustomNameVisible(true);
        if(healthBars.containsKey(entity)){
            entity.setCustomName(healthBars.get(entity).ConstructHealthString(amount));
        }else{
            healthBars.put(entity, new HealthBar(this, entity, amount));
        }
    }

    void EnemyDeathEvent(Entity entity){
        entity.setCustomNameVisible(true);
        if(healthBars.containsKey(entity)){
            entity.setCustomName(healthBars.get(entity).ConstructDeadString());
            healthBars.remove(entity);
        }
    }

    public void RemoveHealthBar(Entity entity){
        healthBars.remove(entity);
    }
}
