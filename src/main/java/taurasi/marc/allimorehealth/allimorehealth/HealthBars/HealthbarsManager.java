package taurasi.marc.allimorehealth.allimorehealth.HealthBars;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import taurasi.marc.allimorecore.StringUtils;

class HealthbarsManager {

   static void NewEnemyEvent(Entity entity){
       entity.setCustomNameVisible(true);
       entity.setCustomName(ConstructHealthString(entity, 0));
   }

   static void EnemyHealthChangeEvent(Entity entity, double amount){
        entity.setCustomNameVisible(true);
        entity.setCustomName(ConstructHealthString(entity, amount));
    }

    static void EnemyDeathEvent(Entity entity){
        entity.setCustomNameVisible(true);
        entity.setCustomName(ConstructDeadString(entity));
    }

    static boolean DoesNotDisplayHealthBar(Entity entity){
        return  !(entity instanceof Damageable) ||
                !(entity instanceof Attributable) ||
                entity.getType() == EntityType.PLAYER;
    }

    private static String ConstructDeadString(Entity entity){
       String entityTypeName = StringUtils.formatEnumString(entity.getType().toString());
       return entityTypeName + ChatColor.RED + " [....................]";
    }

    private static String ConstructHealthString(Entity entity, double damage){
        String entityTypeName = StringUtils.formatEnumString(entity.getType().toString());
        ChatColor healthBarColor = ChatColor.GREEN;

        Damageable damageEntity = (Damageable)entity;
        Attributable attributeEntity = (Attributable)entity;

        double currentHealth = damageEntity.getHealth() - damage;
        double maxHealth = attributeEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double healthPercent = currentHealth / maxHealth;

        char[] healthBarText = new char[20];

        for(int i = 0; i < 20; i++){
            if (healthPercent * 100 >= i * 5){
                healthBarText[i] = '|';
            }else{
                healthBarText[i] = '.';
            }
        }

        if(healthPercent < .30){
            healthBarColor = ChatColor.RED;
        }
        else if (healthPercent < .60){
            healthBarColor = ChatColor.YELLOW;
        }

        return String.format("%s %s[%s]", entityTypeName, healthBarColor, new String(healthBarText));
    }
}
