package de.cintox.hoshire.leveling.util;

import de.cintox.hoshire.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class levelManager {

    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Main.plugin.fileLS);
    Player p;
    private int addL;
    private float oldxps;
    private int oldlevel;

    public levelManager(Player p) {
        this.p = p;
    }

    public double getOldXps() {

        if(cfg.getBoolean(p.getUniqueId() + ".stored")) {
            this.oldxps = (float) cfg.getDouble(p.getUniqueId() + ".old.xp");
        }

        return this.oldxps;
    }

    public void setOldXps(float xp) {
        this.oldxps = xp;
    }

    public boolean isStored() {
        return cfg.getBoolean(p.getUniqueId() + ".stored");
    }

    public int getOldLevel() {

        if(cfg.getBoolean(p.getUniqueId() + ".stored")) {
            this.oldlevel = cfg.getInt(p.getUniqueId() + ".old.level");
        }

        return this.oldlevel;
    }

    public void setOldLevel(int level) {
        this.oldlevel = level;
    }

    public void storeOld() {
        cfg.set(p.getUniqueId() + ".old.level", this.oldlevel);
        cfg.set(p.getUniqueId() + ".old.xp", this.oldxps);
        cfg.set(p.getUniqueId() + ".stored", true);
        this.saveFile();
    }

    public void removeOld() {
        cfg.set(p.getUniqueId() + ".old", null);
        cfg.set(p.getUniqueId() + ".stored", false);
        this.saveFile();
    }

    public int getLevel() {

        if (cfg.get(p.getUniqueId() + ".level") != null) {
            return cfg.getInt(p.getUniqueId() + ".level");
        }
        return 0;
    }

    public void setLevel(int level) {

        cfg.set(p.getUniqueId() + ".level", level);
        this.saveFile();
    }

    public double getExp() {

        if (cfg.get(p.getUniqueId() + ".xp") != null) {
            return (float) cfg.getDouble(p.getUniqueId() + ".xp");
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
        if (exp >= XPNeededForLevel(getLevel() + 1)) {
            exp -= XPNeededForLevel(getLevel() + 1);
            setLevel(getLevel() + 1);
        }

        cfg.set(p.getUniqueId() + ".xp", exp);
        this.saveFile();
    }

    public void addExp(float exp) {
        setExp((float) (getExp() + exp));
    }

    public boolean removeExp(float exp) {
        float crrnt = (float) getExp();
        if (crrnt - exp < 0) {
            return false;
        }
        cfg.set(p.getUniqueId() + ".xp", getExp() - exp);
        this.saveFile();
        return true;
    }

    public int XPNeededForLevel(int level) {
        return 1000 + level * 75;
    }

    private void saveFile() {
        try {
            cfg.save(Main.plugin.fileLS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
