package de.cintox.hoshire.hoshcoins.util;

import de.cintox.hoshire.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class saveC {

    static File file = new File(Main.plugin.getDataFolder(), "coins.yml");
    static YamlConfiguration cfg;

    private static void fillCfg(UUID pUUID) {

        cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(pUUID + ".name", Bukkit.getPlayer(pUUID).getName());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int getCoins(UUID pUUID) {

        YamlConfiguration cfg;

        if (!file.exists()) {
            try {
                file.createNewFile();
                fillCfg(pUUID);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0;


        } else {
            cfg = YamlConfiguration.loadConfiguration(file);

            if (!(Bukkit.getPlayer(pUUID).getName().equals(cfg.get(pUUID + ".name")))) {
                cfg.set(pUUID + ".name", Bukkit.getPlayer(pUUID).getName());
            }

            return cfg.getInt(String.valueOf(pUUID) + ".coins");
        }
    }

    public static boolean setCoins(UUID pUUID, int pCoins) {

        if (!file.exists()) {
            try {
                file.createNewFile();
                fillCfg(pUUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set(String.valueOf(pUUID) + ".coins", pCoins);

        if (!(Bukkit.getPlayer(pUUID).getName().equals(cfg.get(pUUID + ".name")))) {
            cfg.set(pUUID + ".name", Bukkit.getPlayer(pUUID).getName());
        }

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean addCoins(UUID pUUID, int pCoins) {

        if (!file.exists()) {
            try {
                file.createNewFile();
                fillCfg(pUUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int oldcoins = getCoins(pUUID);
        cfg.set(String.valueOf(pUUID) + ".coins", oldcoins + pCoins);

        if (!(Bukkit.getPlayer(pUUID).getName().equals(cfg.get(pUUID + ".name")))) {
            cfg.set(pUUID + ".name", Bukkit.getPlayer(pUUID).getName());
        }

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    public static boolean removeCoins(UUID pUUID, int pCoins) {

        if (!file.exists()) {
            try {
                file.createNewFile();
                fillCfg(pUUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        int oldcoins = getCoins(pUUID);

        cfg.set(String.valueOf(pUUID) + ".coins", oldcoins - pCoins);

        if (!(Bukkit.getPlayer(pUUID).getName().equals(cfg.get(pUUID + ".name")))) {
            cfg.set(pUUID + ".name", Bukkit.getPlayer(pUUID).getName());
        }

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

}
