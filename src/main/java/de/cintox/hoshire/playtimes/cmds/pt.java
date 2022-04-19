package de.cintox.hoshire.playtimes.cmds;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.playtimes.TLManager.ScoreBoard;
import de.cintox.hoshire.playtimes.TimeManager.TimeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class pt implements CommandExecutor {

    boolean sure = false;
    YamlConfiguration cfg = TimeManager.cfg;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("playtime")) {
            if (sender instanceof Player) {

                Player p = (Player) sender;

                if (p.isOp()) {

                    if (args.length == 0) {
                        sendPlayTime(p,p);

                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("help")) {

                            p.sendMessage(" ");
                            p.sendMessage("§7##################§8Help for PlayTimes§7##################");
                            p.sendMessage("§8• §7/playtime §f≫ §8Zeigt deine Spielzeit an§7.");
                            p.sendMessage("§8• §7/playtime §8<§7Spieler§8> §f≫ §8Zeigt die Spielzeit eines Spielers an§7.");
                            p.sendMessage("§8• §7/playtime debug §f≫ §8Aktiviert/Deaktiviert den Test-Modus des Plugins§7.");
                            p.sendMessage("§8#####################§7DEBUG MODE§8#####################");
                            p.sendMessage("  §8↳ §7/playtime set §8<§7Spieler§8> <§7Stunden§8>§7.");
                            p.sendMessage("  §8↳ §7/playtime reset §8<§7Spieler§8>§7.");
                            p.sendMessage("  §8↳ §7/playtime reset§7 §f≫ §4ENTFERNT ALLE DATEN§7.");
                            p.sendMessage("§7##################§8Help for PlayTimes§7##################");
                            p.sendMessage(" ");

                        } else if (args[0].equalsIgnoreCase("debug")) {
                            if (!Main.plugin.debug) {
                                p.sendMessage(Main.plugin.prefix + "§fDEBUG MODE §7wurde §2aktiviert§7.");
                                Main.plugin.debug = true;
                            } else {
                                p.sendMessage(Main.plugin.prefix + "§fDEBUG MODE §7wurde §cdeaktiviert§7.");
                                Main.plugin.debug = false;
                            }


                        } else if (args[0].equalsIgnoreCase("reset")) {
                            if (Main.plugin.debug) {
                                if(sure) {
                                    for (String key : cfg.getKeys(true)) {
                                        cfg.set(key, null);
                                    }

                                    try {
                                        cfg.save(Main.plugin.filePT);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        ScoreBoard.resetSB(players);
                                    }
                                    TimeManager.restartTimer();
                                    p.sendMessage(Main.plugin.prefix + "§fDu hast alle Daten zurückgesetzt§7.");
                                    sure = false;
                                } else {
                                    p.sendMessage(Main.plugin.prefix + "§4Bist du sicher, dass du alle Daten löschen willst? Dann gib den Befehl erneut ein!");
                                    sure = true;
                                }
                            } else {
                                p.sendMessage(Main.plugin.prefix + "§fDu musst zuerst den §4DEBUG MODE §faktivieren!");
                            }

                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {
                                sendPlayTime(p, target);
                            } else {
                                p.sendMessage(Main.plugin.prefix + "§fDieser Spieler ist nicht online!");
                            }
                        }
                    } else if (args.length == 2) {
                        if (Main.plugin.debug) {
                            if (args[0].equalsIgnoreCase("reset")) {
                                Player target = Bukkit.getPlayer(args[1]);
                                if (target != null) {
                                    cfg.set(target.getUniqueId() + ".hours", null);
                                    cfg.set(target.getUniqueId() + ".minutes", null);
                                    cfg.set(target.getUniqueId() + ".days", null);

                                    try {
                                        cfg.save(Main.plugin.filePT);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    ScoreBoard.update(target);
                                    p.sendMessage(Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                                            + "§7 wurde §fzurückgesetzt§7.");
                                } else {
                                    p.sendMessage(Main.plugin.prefix + "§fDieser Spieler ist nicht online!");
                                }
                            } else {
                                p.sendMessage(Main.plugin.prefix + "§fDieser Befehl existiert nicht!");
                            }
                        } else {
                            p.sendMessage(Main.plugin.prefix + "§fDu musst zuerst den DEBUG MODE aktivieren!");
                        }
                    } else if (args.length == 3) {
                        if (Main.plugin.debug) {
                            if (args[0].equalsIgnoreCase("set")) {
                                Player target = Bukkit.getPlayer(args[1]);
                                String thours = args[2];
                                boolean geht = true;
                                for (int i = 0; i < thours.length(); i++) {
                                    if (!Character.isDigit(thours.charAt(i))) {
                                        p.sendMessage(Main.plugin.prefix + "§4An letzter Stelle ist eine Zahl gefordert§8!");
                                        geht = false;
                                        break;
                                    }
                                }
                                if (geht) {
                                    int hours = Integer.parseInt(args[2]);
                                    assert target != null;
                                    cfg.set(target.getUniqueId() + ".minutes", null);
                                    TimeManager.updateTimer(hours, target, p);

                                    try {
                                        cfg.save(Main.plugin.filePT);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            } else {
                                p.sendMessage(Main.plugin.prefix + "§fDieser Befehl existiert nicht!");
                            }
                        } else {
                            p.sendMessage(Main.plugin.prefix + "§fDu musst zuerst den DEBUG MODE aktivieren!");
                        }
                    } else {
                        p.sendMessage(Main.plugin.prefix + "§fDieser Befehl existiert nicht!");
                    }

                } else {
                    if (args.length == 0) {
                        sendPlayTime(p,p);

                    } else if (args.length == 1) {
                        if(args[0].equalsIgnoreCase("help")) {
                            p.sendMessage(" ");
                            p.sendMessage("§7##################§8Help for PlayTimes§7##################");
                            p.sendMessage("§8• §7/playtime §f≫ §8Zeigt deine Spielzeit an§7.");
                            p.sendMessage("§8• §7/playtime §8<§7Spieler§8> §f≫ §8Zeigt die Spielzeit eines Spielers an§7.");
                            p.sendMessage("§7##################§8Help for PlayTimes§7##################");
                            p.sendMessage(" ");
                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {
                                sendPlayTime(p, target);
                            } else {
                                p.sendMessage(Main.plugin.prefix + "§fDieser Spieler ist nicht online!");
                            }
                        }
                    } else {
                        p.sendMessage(Main.plugin.prefix + "§fSyntax. Benutze /playtime help.");
                    }
                }
            } else {
                sender.sendMessage(Main.plugin.prefix + "§fDieser Befehl kann nur von Spielern ausgeführt werden.");
            }
        }
        return false;
    }

    private void sendPlayTime(Player p, Player target) {

        if (cfg.get(target.getUniqueId() + ".minutes") != null
                || cfg.get(target.getUniqueId() + ".hours") != null) {

            int hours = 0;
            int minutes = 0;

            if (cfg.get(target.getUniqueId() + ".hours") != null) {
                hours = cfg.getInt(target.getUniqueId() + ".hours");
                p.sendMessage(hours + "");
                p.sendMessage("GGD: " + cfg.getInt(target.getUniqueId() + ".hours"));
            }

            if(cfg.get(target.getUniqueId() + ".minutes") != null) {
                minutes = cfg.getInt(target.getUniqueId() + ".minutes");
                p.sendMessage(minutes + "");
                p.sendMessage("GGD2: " + cfg.getInt(target.getUniqueId() + ".minutes"));
            }

            int nhours = hours;
            int days = 0;

            if (hours >= 24) {
                days = (hours / 24);
                nhours = hours - (days * 24);
            }


            String oneone = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §feine Stunde §7und §feine Minute§7.";
            String onemore = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §feine Stunde §7und §f" + minutes + " Minuten§7.";
            String moreone = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + hours + " Stunden §7und §feine Minute§7.";
            String moremore = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + hours + " Stunden §7und §f" + minutes + " Minuten§7.";

            String dayone = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §feinen Tag §7und §feine Stunde§7.";
            String daysone = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + days + " Tage §7und §feine Stunde§7.";
            String daymore = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §feinen Tag §7und §f" + nhours + " Stunden§7.";
            String daysmore = Main.plugin.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + days + " Tage §7und §f" + nhours + " Stunden§7.";

            if (days == 0) {
                if (hours == 1 && minutes == 1) {
                    p.sendMessage(oneone);
                } else if (hours == 1) {
                    p.sendMessage(onemore);
                } else if (minutes == 1) {
                    p.sendMessage(moreone);
                } else {
                    p.sendMessage(moremore);
                }
            } else {
                if (days == 1 && nhours == 1) {
                    p.sendMessage(dayone);
                } else if (nhours == 1) {
                    p.sendMessage(daysone);
                } else if (days == 1) {
                    p.sendMessage(daymore);
                } else {
                    p.sendMessage(daysmore);
                }
            }
            p.sendMessage("HAA");

        } else {
            p.sendMessage(Main.plugin.prefix + "§fFür diesen Spieler gibt es keine Aufzeichnungen.");
        }

    }
}
