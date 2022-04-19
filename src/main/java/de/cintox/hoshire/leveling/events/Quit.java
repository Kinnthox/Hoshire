package de.cintox.hoshire.leveling.events;

import de.cintox.hoshire.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Main.plugin.xps.containsKey(e.getPlayer())) Main.plugin.xps.get(e.getPlayer()).storeOld();
        Main.plugin.xps.remove(e.getPlayer());
    }

}
