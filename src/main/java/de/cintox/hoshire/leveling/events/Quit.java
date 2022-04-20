package de.cintox.hoshire.leveling.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage("§f" + e.getPlayer().getName() + " left the game");
    }

}
