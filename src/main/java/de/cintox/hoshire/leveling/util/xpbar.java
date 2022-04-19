package de.cintox.hoshire.leveling.util;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.util.strings;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class xpbar {

    public HashMap<Player, levelManager> xps = Main.plugin.xps;
    public HashMap<Player, levelManager> oldxps = Main.plugin.oldxps;
    File file = new File(Main.plugin.getDataFolder(), "levels.yml");
    YamlConfiguration cfg;

    public xpbar() {
        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            cfg = YamlConfiguration.loadConfiguration(file);

        }
    }

    public void showbar(Player p) {

        cfg = YamlConfiguration.loadConfiguration(file);
        xps.put(p, new levelManager(p));
        oldxps.put(p, new levelManager(p));

        oldxps.get(p).setExp(p.getExp());
        oldxps.get(p).setLevel(p.getLevel());

        float xp = xps.get(p).getExp();
        int level = xps.get(p).getLevel();

        p.setLevel(level);
        p.setExp(xp/300F);
        p.sendMessage(strings.XPChange);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

            @Override
            public void run() {

                float exp = (oldxps.get(p).getExp());
                while((exp) >= 1) {
                    oldxps.get(p).setLevel(oldxps.get(p).getLevel() + 1);
                   exp -= 1;
                }
                p.setLevel(oldxps.get(p).getLevel());
                p.setExp(exp);
                p.giveExp(oldxps.get(p).getLater());
                p.sendMessage(strings.XPReset);
                oldxps.remove(p);
            }

        }, 10*20);

    }

}
