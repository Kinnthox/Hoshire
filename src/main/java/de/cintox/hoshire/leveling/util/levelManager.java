package de.cintox.hoshire.leveling.util;

import de.cintox.hoshire.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class levelManager {

    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Main.plugin.fileLS);

    public static void setLevel(Player p, int level) {
        cfg.set(p.getUniqueId() + ".level", level + 1);
        saveFile();
    }

    public static int getLevel(Player p) {

        if (cfg.get(p.getUniqueId() + ".level") != null) {
            return cfg.getInt(p.getUniqueId() + ".level");
        }
        return 1;
    }

    public static void setLater(Player p, int xp) {
        cfg.set(p.getUniqueId() + ".old.later", xp);
        saveFile();
    }

    public static int getLater(Player p) {
        return cfg.getInt(p.getUniqueId() + ".old.later");
    }

    public static void setExp(Player p, int exp) {

        setLevel(p, 0);
        float xpbar = exp / XPNeededForLevel(getLevel(p));

        while (xpbar >= 1) {
            setLevel(p, getLevel(p));
            xpbar--;
        }

        cfg.set(p.getUniqueId() + ".xp", xpbar);
        saveFile();

    }

    public static void setOld(Player p, int level, float xp) {
        cfg.set(p.getUniqueId() + ".old.level", level);
        cfg.set(p.getUniqueId() + ".old.xp", xp);
        saveFile();
    }

    public static int getOldLevel(Player p) {
        return cfg.getInt(p.getUniqueId() + ".old.level");
    }

    public static void removeOld(Player p) {
        if(pOnline(p)) {
            cfg.set(p.getUniqueId() + ".old", null);
        }
    }

    public static float getOldXP(Player p) {
        return (float) cfg.getDouble(p.getUniqueId() + ".old.xp");
    }

    /**
     * Gibt die PROZENTZAHL zurück!
     */
    public static float getExp(Player p) {
        return (float) cfg.getDouble(p.getUniqueId() + ".xp");
    }

    /**
     * Gibt die GESAMTZAHL zurück!
     */
    public static float getFullXP(Player p) {
        return (getExp(p) * XPNeededForLevel(getLevel(p)));
    }

    public static void addExp(Player p, int exp) {
        float adding = exp / XPNeededForLevel(getLevel(p));
        float current = getExp(p);
        float newExp = current + adding;

        while (newExp >= 1) {
            setLevel(p, getLevel(p));
            newExp--;
        }
        cfg.set(p.getUniqueId() + ".xp", newExp);
        saveFile();
    }

    public static boolean removeExp(Player p, int exp) {
        float removing = exp / XPNeededForLevel(getLevel(p));
        float current = getExp(p);
        if (current - removing < 0) {
            return false;
        }
        float newExp = current - removing;

        cfg.set(p.getUniqueId() + ".xp", newExp);
        saveFile();
        return true;
    }

    private static float XPNeededForLevel(int level) {
        return 1000 + level * 75;
    }

    private static void saveFile() {
        try {
            cfg.save(Main.plugin.fileLS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean pOnline(Player p) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.getName().equals(p.getName())) {
                return true;
            }
        }
        return false;
    }

}
