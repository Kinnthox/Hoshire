package de.cintox.hoshire.leveling.events;

import de.cintox.hoshire.leveling.util.levelManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    static YamlConfiguration cfg = levelManager.cfg;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if(cfg.get(p.getUniqueId() + ".old") != null) {
            p.setLevel(levelManager.getOldLevel(p));
            p.setExp(levelManager.getOldXP(p));
        }
    }
}
