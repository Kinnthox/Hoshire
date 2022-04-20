package de.cintox.hoshire.playtimes.TLManager;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.playtimes.TimeManager.TimeManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.IOException;
import java.util.Objects;


public class ScoreBoard implements Listener {

    static Scoreboard board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
    static YamlConfiguration cfg = TimeManager.cfg;
    static Team team = null;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage("§f" + p.getName() + "§f joined the game");

        if (board.getTeam(p.getName()) == null) {
            team = board.registerNewTeam(p.getName());
        } else {
            team = board.getTeam(p.getName());
        }
        assert team != null;
        team.addEntry(p.getName());

        update(p);

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(p.isOp()) {
            Main.plugin.debug = false;
        }
        e.setQuitMessage("§e" + p.getName() + "§e left the game");
        if(p.isBanned()) {
            cfg.set(String.valueOf(p.getUniqueId()), null);
            try {
                cfg.save(Main.plugin.filePT);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void update(Player p) {

        if (cfg.get(p.getUniqueId() + ".hours") != null) {
            int hours = cfg.getInt(p.getUniqueId() + ".hours");
            team = board.getTeam(p.getName());
            assert team != null;
            if (hours >= 20 && hours < 30) {
                team.setSuffix(" ⁕");
            } else if (hours >= 30 && hours < 40) {
                team.setSuffix(" ✬");
            } else if (hours >= 40) {
                team.setSuffix(" ❃");
            } else {
                team.setSuffix("");
            }
        } else {
            team.setSuffix("");
        }
    }

    public static void resetSB(Player p) {
        if (board.getTeam(p.getName()) != null) {
            Objects.requireNonNull(board.getTeam(p.getName())).setSuffix("");
        }
    }
}
