package de.cintox.hoshire.leveling.events;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.leveling.util.levelManager;
import de.cintox.hoshire.util.strings;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        HashMap<Player, levelManager> xps = Main.plugin.xps;
        xps.put(p, new levelManager(p));

        if(xps.get(p).isStored()) {
            System.out.println(e.getPlayer().getLevel() + "");
            System.out.println(e.getPlayer().getExp());
            System.out.println("HERll");

            float exp = (float) xps.get(p).getOldXps();
            while ((exp) >= 1) {
                xps.get(p).setOldLevel(xps.get(p).getOldLevel() + 1);
                exp -= 1;
            }
            p.setLevel(xps.get(p).getOldLevel());
            p.setExp(exp);
            p.giveExp(xps.get(p).getLater());
            xps.get(p).removeOld();
        }
        xps.remove(p);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(e.getMessage().startsWith("hund")) {
            HashMap<Player, levelManager> xps = Main.plugin.xps;
            if(xps.containsKey(e.getPlayer())) {
                e.getPlayer().sendMessage("JA!");
            } else {
                e.getPlayer().sendMessage("NEIN!");
            }
         }
    }

}
