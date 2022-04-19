package de.cintox.hoshire;

import de.cintox.hoshire.hoshcoins.cmds.coins;
import de.cintox.hoshire.hoshcoins.events.listeners;
import de.cintox.hoshire.leveling.cmds.aw;
import de.cintox.hoshire.leveling.events.Join;
import de.cintox.hoshire.leveling.events.Quit;
import de.cintox.hoshire.leveling.events.XP;
import de.cintox.hoshire.leveling.util.levelManager;
import de.cintox.hoshire.playtimes.TLManager.ScoreBoard;
import de.cintox.hoshire.playtimes.TimeManager.TimeManager;
import de.cintox.hoshire.playtimes.cmds.pt;
import de.cintox.hoshire.util.strings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    public HashMap<Player, levelManager> xps = new HashMap<>();
    public static Main plugin;
    public boolean debug = false;

    public File filePT = new File(getDataFolder(), "PlayTime.yml");
    public File fileHC = new File(getDataFolder(), "Hoshcoins.yml");
    public File fileLS = new File(getDataFolder(), "LevelSystem.yml");

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(strings.prefix + ChatColor.GRAY + "Das Plugin wurde " +
                ChatColor.GREEN + "geladen" + ChatColor.GRAY + ".");
        plugin = this;
        TimeManager.startTimer();
        checkFileCreation();

        getServer().getPluginManager().registerEvents(new ScoreBoard(), this);
        getServer().getPluginManager().registerEvents(new listeners(), this);
        getServer().getPluginManager().registerEvents(new XP(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getCommand("playtime").setExecutor(new pt());
        getCommand("coins").setExecutor(new coins());
        getCommand("way").setExecutor(new aw());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(strings.prefix + ChatColor.GRAY + "Das Plugin wurde " +
                ChatColor.RED + "beendet" + ChatColor.GRAY + ".");
    }

    private void checkFileCreation() {

        if(!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        if(!filePT.exists()) {
            try {
                filePT.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!fileHC.exists()) {
            try {
                fileHC.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!fileLS.exists()) {
            try {
                fileLS.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
