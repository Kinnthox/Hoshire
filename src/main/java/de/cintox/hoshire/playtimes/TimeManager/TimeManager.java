package de.cintox.hoshire.playtimes.TimeManager;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.playtimes.TLManager.ScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class TimeManager {

    static File file = new File(Main.plugin.getDataFolder(), "PlayTime.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void startTimer() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {

            for (Player players : Bukkit.getOnlinePlayers()) {
                cfg.set(players.getUniqueId() + ".name", players.getName());
                int days = cfg.getInt(players.getUniqueId() + ".days");
                int hours = cfg.getInt(players.getUniqueId() + ".hours");
                int minutes = cfg.getInt(players.getUniqueId() + ".minutes");

                minutes++;

                cfg.set(players.getUniqueId() + ".minutes", minutes);

                try {
                    cfg.save(Main.plugin.filePT);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (minutes == 60) {
                    cfg.set(players.getUniqueId() + ".minutes", 0);
                    hours++;
                    cfg.set(players.getUniqueId() + ".hours", hours);

                    try {
                        cfg.save(Main.plugin.filePT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ScoreBoard.update(players);
                }

                if (hours == 24) {
                    cfg.set(players.getUniqueId() + ".hours", 0);
                    days++;
                    cfg.set(players.getUniqueId() + ".days", days);

                    try {
                        cfg.save(Main.plugin.filePT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ScoreBoard.update(players);
                }

            }
        }, 20 * 60, 20 * 60);
    }

    public static void updateTimer(int nhours, Player target, Player p) { //321

        cfg.set(target.getUniqueId() + ".hours", nhours);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(nhours == 1) {
            p.sendMessage(Main.plugin.prefix + "§7 Die Spielzeit von §f" + target.getName()
                    + " §7wurde auf §feine Stunde §7gesetzt.");
        } else {
            p.sendMessage(Main.plugin.prefix + "§7 Die Spielzeit von §f" + target.getName()
                    + " §7wurde auf §f" + nhours + " Stunden §7gesetzt.");
        }

        ScoreBoard.update(target);
    }

    public static void restartTimer() {
        Bukkit.getScheduler().cancelTasks(Main.plugin);
        startTimer();
    }

}
