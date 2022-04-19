package de.cintox.hoshire.hoshcoins.events;

import de.cintox.hoshire.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.IOException;

public class listeners implements Listener {

    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Main.plugin.fileHC);

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

            if(!p.hasPlayedBefore()) {
                cfg.set(p.getUniqueId() + ".name", p.getName());
                cfg.set(p.getUniqueId() + ".coins", 100);

                try {
                    cfg.save(Main.plugin.fileHC);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }