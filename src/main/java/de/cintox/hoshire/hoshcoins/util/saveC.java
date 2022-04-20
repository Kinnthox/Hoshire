package de.cintox.hoshire.hoshcoins.util;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.leveling.util.levelManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class saveC {

    static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Main.plugin.fileHC);

    private static void fillCfg(UUID pUUID) {

        cfg.set(pUUID + ".name", Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName());

        try {
            cfg.save(Main.plugin.fileHC);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int getCoins(UUID pUUID) {

        if (!(Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName().equals(cfg.get(pUUID + ".name")))) {
            cfg.set(pUUID + ".name", Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName());

            try {
                cfg.save(Main.plugin.fileHC);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return cfg.getInt(pUUID + ".coins");
    }


    public static boolean setCoins(UUID pUUID, int pCoins) {

        fillCfg(pUUID);
        cfg.set(pUUID + ".coins", pCoins);
        levelManager.setExp(Bukkit.getPlayer(pUUID), pCoins);

        if (!(Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName().equals(cfg.get(pUUID + ".name")))) {
            cfg.set(pUUID + ".name", Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName());
        }

        try {
            cfg.save(Main.plugin.fileHC);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean addCoins(UUID pUUID, int pCoins) {

        fillCfg(pUUID);

        int oldcoins = getCoins(pUUID);
        cfg.set(pUUID + ".coins", oldcoins + pCoins);
        levelManager.addExp(Bukkit.getPlayer(pUUID), pCoins);

        if (!(Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName().equals(cfg.get(pUUID + ".name")))) {
            cfg.set(pUUID + ".name", Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName());
        }

        try {
            cfg.save(Main.plugin.fileHC);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    public static boolean removeCoins(UUID pUUID, int pCoins) {

        fillCfg(pUUID);

        int oldcoins = getCoins(pUUID);

        if((oldcoins - pCoins) < 0) {
            return false;
        }
        cfg.set(pUUID + ".coins", oldcoins - pCoins);
        levelManager.setExp(Bukkit.getPlayer(pUUID), getCoins(pUUID));
        if (!(Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName().equals(cfg.get(pUUID + ".name")))) {
            cfg.set(pUUID + ".name", Objects.requireNonNull(Bukkit.getPlayer(pUUID)).getName());
        }

        try {
            cfg.save(Main.plugin.fileHC);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

}
