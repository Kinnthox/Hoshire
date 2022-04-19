package de.cintox.hoshire.playtimes.TimeManager;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.playtimes.TLManager.ScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TimeManager {

    public static void startTimer() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            @Override
            public void run() {

                for (Player players : Bukkit.getOnlinePlayers()) {
                    Main.plugin.getConfig().set(players.getUniqueId() + ".name", players.getName());
                    int days = Main.plugin.getConfig().getInt(players.getUniqueId() + ".days");
                    int hours = Main.plugin.getConfig().getInt(players.getUniqueId() + ".hours");
                    int minutes = Main.plugin.getConfig().getInt(players.getUniqueId() + ".minutes");

                    minutes++;

                    Main.plugin.getConfig().set(players.getUniqueId() + ".minutes", minutes);
                    Main.plugin.saveConfig();

                    if (minutes == 60) {
                        Main.plugin.getConfig().set(players.getUniqueId() + ".minutes", 0);
                        hours++;
                        Main.plugin.getConfig().set(players.getUniqueId() + ".hours", hours);
                        Main.plugin.saveConfig();
                        ScoreBoard.update(players);
                    }

                    if (hours == 24) {
                        Main.plugin.getConfig().set(players.getUniqueId() + ".hours", 0);
                        days++;
                        Main.plugin.getConfig().set(players.getUniqueId() + ".days", days);
                        Main.plugin.saveConfig();
                        ScoreBoard.update(players);
                    }

                }
            }
        }, 20 * 60, 20 * 60);
    }

    public static void updateTimer(int nhours, Player target, Player p) { //321

        Main.plugin.getConfig().set(target.getUniqueId() + ".hours", nhours);
        Main.plugin.saveConfig();

        if(nhours == 1) {
            p.sendMessage(Main.plugin.prefix + "§7 Die Spielzeit von §c" + target.getName()
                    + " §7wurde auf §ceine Stunde §7gesetzt.");
        } else {
            p.sendMessage(Main.plugin.prefix + "§7 Die Spielzeit von §c" + target.getName()
                    + " §7wurde auf §c" + nhours + " Stunden §7gesetzt.");
        }

        ScoreBoard.update(target);
    }

    public static void restartTimer() {
        Bukkit.getScheduler().cancelTasks(Main.plugin);
        startTimer();
    }

}
