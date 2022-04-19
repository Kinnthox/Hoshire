package de.cintox.hoshire.hoshcoins.events;

import de.cintox.hoshire.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class listeners implements Listener {

    static File file = new File(Main.plugin.getDataFolder(),"coins.yml");
    YamlConfiguration cfg;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(!p.hasPlayedBefore()) {
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            cfg = YamlConfiguration.loadConfiguration(file);

            cfg.set(p.getUniqueId() + ".name", p.getName());
            cfg.set(p.getUniqueId() + ".coins", 100);

            try {
                cfg.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
