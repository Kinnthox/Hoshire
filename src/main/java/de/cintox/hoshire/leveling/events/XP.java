package de.cintox.hoshire.leveling.events;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.leveling.util.levelManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import java.util.HashMap;

public class XP implements Listener {

    HashMap<Player, levelManager> oldxps = Main.plugin.oldxps;

    @EventHandler
    public void onEXP(PlayerExpChangeEvent e) {

        if (oldxps.containsKey(e.getPlayer())) {
            oldxps.get(e.getPlayer()).addLater(e.getAmount());
            e.setAmount(0);
        }
    }

}
