package de.cintox.hoshire;

import de.cintox.hoshire.hoshcoins.cmds.coins;
import de.cintox.hoshire.hoshcoins.events.listeners;
import de.cintox.hoshire.leveling.cmds.aw;
import de.cintox.hoshire.leveling.events.XP;
import de.cintox.hoshire.leveling.util.levelManager;
import de.cintox.hoshire.playtimes.TLManager.ScoreBoard;
import de.cintox.hoshire.playtimes.TimeManager.TimeManager;
import de.cintox.hoshire.playtimes.cmds.pt;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    public final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Hoshire" + ChatColor.DARK_GRAY + "] ";
    public HashMap<Player, levelManager> xps = new HashMap<Player, levelManager>();
    public HashMap<Player, levelManager> oldxps = new HashMap<Player, levelManager>();
    public static Main plugin;
    public boolean debug = false;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GRAY + "Das Plugin wurde " +
                ChatColor.GREEN + "geladen" + ChatColor.GRAY + ".");
        plugin = this;
        TimeManager.startTimer();
        getServer().getPluginManager().registerEvents(new ScoreBoard(), this);
        getServer().getPluginManager().registerEvents(new listeners(), this);
        getServer().getPluginManager().registerEvents(new XP(), this);
        getCommand("playtime").setExecutor(new pt());
        getCommand("coins").setExecutor(new coins());
        getCommand("way").setExecutor(new aw());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GRAY + "Das Plugin wurde " +
                ChatColor.RED + "beendet" + ChatColor.GRAY + ".");
    }
}
