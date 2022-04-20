package de.cintox.hoshire.leveling.util;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.util.strings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class xpbar {

    public void showbar(Player p) {

        Main.plugin.blocked.add(p);
        float xp = levelManager.getExp(p);
        int level = levelManager.getLevel(p);
        levelManager.setOld(p, p.getLevel(), p.getExp());

        p.setLevel(level);
        p.setExp(xp);
        p.sendMessage(strings.XPChange);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

            ResetBar(p);
            Main.plugin.blocked.remove(p);
        }, 10 * 20);
    }

    public void ResetBar(Player p) {
        float exp = levelManager.getOldXP(p);
        while ((exp) >= 1) {
            levelManager.setOld(p, levelManager.getOldLevel(p) + 1, levelManager.getOldXP(p));
            exp -= 1;
        }
        p.setLevel(levelManager.getOldLevel(p));
        p.setExp(exp);
        p.giveExp(levelManager.getLater(p));
        levelManager.removeOld(p);
        p.sendMessage(strings.XPReset);
    }

}
