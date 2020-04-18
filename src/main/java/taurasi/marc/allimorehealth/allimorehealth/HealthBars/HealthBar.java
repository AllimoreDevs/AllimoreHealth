package taurasi.marc.allimorehealth.allimorehealth.HealthBars;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitTask;
import taurasi.marc.allimorecore.StringUtils;
import taurasi.marc.allimorehealth.allimorehealth.Allimorehealth;

public class HealthBar {
    private static final long healthBarVisTime = 200;

    private String originalName;
    private boolean hadOriginalName;
    private Entity entity;
    private HealthbarsManager healthbarsManager;
    BukkitTask task;

    public HealthBar(HealthbarsManager healthbarsManager, Entity entity, double damageBeingRecieved){
        this.entity = entity;
        this.healthbarsManager = healthbarsManager;
        if(entity.getCustomName() != null &&
                !(entity.getCustomName().equals("")) ){
            originalName = entity.getCustomName();
            hadOriginalName = true;
        }else{
            originalName = StringUtils.formatEnumString(entity.getType().name());
            hadOriginalName = false;
        }

        if(entity.isDead()){
            entity.setCustomName(ConstructDeadString());
        }else{
            entity.setCustomName(ConstructHealthString(damageBeingRecieved));
        }

        task = new RemoveHealthBarTask(this).runTaskLater(Allimorehealth.INSTANCE, healthBarVisTime);
    }

    public void RemoveHealthBar(){
        if(hadOriginalName){
            entity.setCustomName(originalName);
        }else{
            entity.setCustomName("");
            entity.setCustomNameVisible(false);
        }
        healthbarsManager.RemoveHealthBar(entity);
    }

    public String ConstructDeadString(){
        task.cancel();
        return originalName + ChatColor.RED + " [....................]";
    }
    public String ConstructHealthString(double damage){
        if(task != null){
            task.cancel();
            task = new RemoveHealthBarTask(this).runTaskLater(Allimorehealth.INSTANCE, healthBarVisTime);
        }
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

        return String.format("%s %s[%s]", originalName, healthBarColor, new String(healthBarText));
    }

    public static boolean DoesNotDisplayHealthBar(Entity entity){
        return  !(entity instanceof Damageable) ||
                !(entity instanceof Attributable) ||
                entity.getType() == EntityType.PLAYER;
    }
}
