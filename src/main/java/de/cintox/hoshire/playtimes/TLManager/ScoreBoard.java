package de.cintox.hoshire.playtimes.TLManager;

import de.cintox.hoshire.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreBoard implements Listener {

    static Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    static Team team = null;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage("§e" + p.getName() + "§e joined the game");
        if (board.getTeam(p.getName()) == null) {
            team = board.registerNewTeam(p.getName());
        } else {
            team = board.getTeam(p.getName());
        }
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
            Main.plugin.getConfig().set(String.valueOf(p.getUniqueId()), null);
            Main.plugin.saveConfig();
        }
    }

    public static void update(Player p) {

        if (Main.plugin.getConfig().get(p.getUniqueId() + ".hours") != null) {
            int hours = (int) Main.plugin.getConfig().get(p.getUniqueId() + ".hours");
            team = board.getTeam(p.getName());
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
            board.getTeam(p.getName()).setSuffix("");
        }
    }
}
