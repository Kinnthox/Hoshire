package de.cintox.hoshire.leveling.events;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.leveling.util.levelManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class XP implements Listener {

    @EventHandler
    public void onEXP(PlayerExpChangeEvent e) {

        if(Main.plugin.blocked.contains(e.getPlayer())) {
            levelManager.setLater(e.getPlayer(), e.getAmount());
            e.setAmount(0);
        }
    }
}
