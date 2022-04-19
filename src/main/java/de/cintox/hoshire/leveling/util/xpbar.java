package de.cintox.hoshire.leveling.util;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.util.strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class xpbar {

    public HashMap<Player, levelManager> xps = Main.plugin.xps;

    public void showbar(Player p) {

        xps.put(p, new levelManager(p));

        xps.get(p).setOldXps(p.getExp());
        xps.get(p).setOldLevel(p.getLevel());

        float xp = (float) xps.get(p).getExp();
        int level = xps.get(p).getLevel();

        p.setLevel(level);
        p.setExp(xp);
        p.sendMessage(strings.XPChange);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

            if(xps.get(p) != null) {
                ResetBar(p);
            } else {
                Main.plugin.getServer().getConsoleSender().sendMessage(Main.plugin.prefix + ChatColor.RED + "Konnte Level nicht wiederherstellen, da Spieler den Server verlassen hat."
                        + " Sobald er den Server erneut betritt, werden seine XP wiederhergestellt!");
            }
        }, 10*20);
    }

    public void ResetBar(Player p) {
        float exp = (float) xps.get(p).getOldXps();
        while ((exp) >= 1) {
            xps.get(p).setOldLevel(xps.get(p).getOldLevel() + 1);
            exp -= 1;
        }
        p.setLevel(xps.get(p).getOldLevel());
        p.setExp(exp);
        p.giveExp(xps.get(p).getLater());
        p.sendMessage(strings.XPReset);
        xps.remove(p);
    }

}
