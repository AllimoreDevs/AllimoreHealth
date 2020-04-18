package taurasi.marc.allimorehealth.allimorehealth;

public class MobLevelData {
    private String name;
    private int minLevel;

    public MobLevelData(String name, int minLevel){
        this.name = name;
        this.minLevel = minLevel;
    }

    public String getName() {
        return name;
    }

    public int getMinLevel() {
        return minLevel;
    }
}
