package de.cintox.hoshire.leveling.util;

import de.cintox.hoshire.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class levelManager {

    File file = new File(Main.plugin.getDataFolder(), "levels.yml");
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    Player p;
    private int addL;

    public levelManager(Player p) {
        this.p = p;
    }

    public int getLevel() {
        if(cfg.get(p.getUniqueId() + ".level") != null) {
            return cfg.getInt(p.getUniqueId() + ".level");
        }
        return 0;
    }

    public void setLevel(int level) {
        cfg.set(p.getUniqueId() + ".level", level);
    }

    public float getExp() {
        if(cfg.get(p.getUniqueId() + ".xp") != null) {
            return (float) cfg.get(p.getUniqueId() + ".xp");
        }
        return 0;
    }

    public void addLater(int amnt) {
        addL += amnt;
    }

    public int getLater() {
        return addL;
    }

    public void setExp(float exp) {
        cfg.set(p.getUniqueId() + ".xp", exp);
        if(exp >= XPNeededForLevel(getLevel() + 1)) {
            exp -= XPNeededForLevel(getLevel() + 1);
            setLevel(getLevel() + 1);
        }
    }

    public void addExp(float exp) {
        setExp(getExp() + exp);
    }

    public boolean removeExp(float exp) {
        float crrnt = getExp();
        if(crrnt - exp < 0) {
            return false;
        }
        cfg.set(p.getUniqueId() + ".xp", getExp() - exp);
        return true;
    }

    public int XPNeededForLevel(int level) {
        return 1000 + level * 75;
    }

}
