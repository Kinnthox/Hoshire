package de.cintox.hoshire.util;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.hoshcoins.util.saveC;
import org.bukkit.ChatColor;

public class strings {

    public static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Hoshire" + ChatColor.DARK_GRAY + "] ";

    public static String LevelQuit = prefix + ChatColor.RED + "Konnte Level nicht wiederherstellen, da Spieler den Server verlassen hat."
            + " Sobald er den Server erneut betritt, werden seine XP wiederhergestellt!";

    public static String syntaxError = prefix + "§fSyntax§8! §7Verwende §8/§fcoins help§7, um §fHilfe §7zu erhalten.";

    public static String syntaxPT = prefix + "§fSyntax. Benutze /playtime help.";

    public static String onlyPlayer = prefix + "§fDieser Befehl kann nur von Spielern ausgeführt werden.";

    public static String Error = prefix + "§fEin Fehler ist aufgetreten!";

    public static String higherCoins = prefix + "§fDu versuchst dem Spieler mehr Hoshcoins zu entfernen, als er überhaupt besitzt!";

    public static String XPReset = prefix + "§fDu siehst nun wieder deine normalen XP!";

    public static String XPChange = prefix + "§fDu siehst nun dein Level in der XP-Leiste!";

    public static String activeDebug = prefix + "§fDEBUG MODE §7wurde §2aktiviert§7.";

    public static String inactiveDebug = prefix + "§fDEBUG MODE §7wurde §cdeaktiviert§7.";

    public static String resetAll = prefix + "§fDu hast alle Daten zurückgesetzt§7.";

    public static String sure = prefix + "§4Bist du sicher, dass du alle Daten löschen willst? Dann gib den Befehl erneut ein!";

    public static String NeedDebug = prefix + "§fDu musst zuerst den §4DEBUG MODE §faktivieren!";

    public static String PnotOnline = prefix + "§fDieser Spieler ist nicht online!";

    public static String playTimePReset = prefix + "§7Die Spielzeit von §f%PLAYERNAME% §7 wurde §fzurückgesetzt§7.";

    public static String unknownCmd = prefix + "§fDieser Befehl existiert nicht!";

    public static String needNumb = prefix + "§4An letzter Stelle ist eine Zahl gefordert§8!";

    public static String oneone = prefix + "§7Die Spielzeit von §f%PLAYERNAME% §7beträgt §feine Stunde §7und §feine Minute§7.";

    public static String noInfo = prefix + "§fFür diesen Spieler gibt es keine Aufzeichnungen.";

    public static String curHC = prefix + "§7Du hast aktuell §f%COINS% §6Hoshcoins§7.";

    public static String curHCOther = prefix + "§7Spieler §f%NAME% §7besitzt §f%COINS% §6Hoshcoins§7.";

    public static String setHC = prefix + "§7Die §6Hoshcoins §7von §f%NAME% §7 wurden auf §f%COINS% §6Hoshcoins §7gesetzt.";

    public static String addHC = prefix + "§7Dem Konto von §f%NAME% §7 wurden §f%COINS% §6Hoshcoins §7gutgeschrieben.";

    public static String removeHC = prefix + "§7Vom Konto von §f%NAME% §7 wurden §f%COINS% §6Hoshcoins §7entfernt.";

    public static String setPT = prefix + "§7 Die Spielzeit von §f%NAME% §7wurde auf §feine Stunde §7gesetzt.";

    public static String setPT1 = prefix + "§7 Die Spielzeit von §f%NAME% §7wurde auf §f%HOURS% Stunden §7gesetzt.";

    public static String ptHelp = " \n"
            + "§7##################§8Help for PlayTimes§7################## \n"
            + "§8• §7/playtime §f≫ §8Zeigt deine Spielzeit an§7. \n"
            + "§8• §7/playtime §8<§7Spieler§8> §f≫ §8Zeigt die Spielzeit eines Spielers an§7. \n "
            + "§7##################§8Help for PlayTimes§7################## \n" + " ";

    public static String ptHelpOp = " \n"
            + "§7##################§8Help for PlayTimes§7################## \n"
            + "§8• §7/playtime §f≫ §8Zeigt deine Spielzeit an§7. \n"
            + "§8• §7/playtime §8<§7Spieler§8> §f≫ §8Zeigt die Spielzeit eines Spielers an§7. \n "
            + "§8• §7/playtime debug §f≫ §8Aktiviert/Deaktiviert den Test-Modus des Plugins§7. \n"
            + "§8#####################§7DEBUG MODE§8##################### \n"
            + "  §8↳ §7/playtime set §8<§7Spieler§8> <§7Stunden§8>§7. \n"
            + "  §8↳ §7/playtime reset §8<§7Spieler§8>§7. \n"
            + "  §8↳ §7/playtime reset§7 §f≫ §4ENTFERNT ALLE DATEN§7. \n"
            + "§7##################§8Help for PlayTimes§7################## \n" + " ";

    public static String help =
            "§7--------------------- §8[§6Hoshcoins§8] §7--------------------- \n"
            + "§8» §f/coins §8» §7Gibt die Anzahl deiner §6Hoshcoins§7 zurück. \n"
            + "§7--------------------- §8[§6Hoshcoins§8] §7---------------------";

    public static String helpOP =
            "§7##################### §8[§6Hoshcoins§8] §7##################### \n"
            + "§8» §f/coins §8» §7Anzahl deiner §6Hoshcoins§7. \n"
            + "§8» §f/coins §8<§fSpieler§8> » §6Hoshcoins §7eines anderen Spielers. \n"
            + "§8» §f/coins set §8<§fSpieler§8> §8<§fAnzahl§8> » §7Setze die §6Hoshcoins §7eines anderen Spielers. \n"
            + "§8» §f/coins add §8<§fSpieler§8> §8<§fAnzahl§8> » §7Fügt §6Hoshcoins §7auf ein Konto hinzu. \n"
            + "§8» §f/coins remove §8<§fSpieler§8> §8<§fAnzahl§8> » §7Entfernt §6Hoshcoins §7von einem Konto. \n"
            + "§7##################### §8[§6Hoshcoins§8] §7#####################";

}
