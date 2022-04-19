package de.cintox.hoshire.playtimes.cmds;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.playtimes.TLManager.ScoreBoard;
import de.cintox.hoshire.playtimes.TimeManager.TimeManager;
import de.cintox.hoshire.util.strings;
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

                           p.sendMessage(strings.ptHelpOp);

                        } else if (args[0].equalsIgnoreCase("debug")) {
                            if (!Main.plugin.debug) {
                                p.sendMessage(strings.activeDebug);
                                Main.plugin.debug = true;
                            } else {
                                p.sendMessage(strings.inactiveDebug);
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
                                    p.sendMessage(strings.resetAll);
                                    sure = false;
                                } else {
                                    p.sendMessage(strings.sure);
                                    sure = true;
                                }
                            } else {
                                p.sendMessage(strings.NeedDebug);
                            }

                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {
                                sendPlayTime(p, target);
                            } else {
                                p.sendMessage(strings.ptHelpOp);
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
                                    p.sendMessage(strings.playTimePReset.replace("%PLAYERNAME%", target.getName()));
                                } else {
                                    p.sendMessage(strings.PnotOnline);
                                }
                            } else {
                                p.sendMessage(strings.unknownCmd);
                            }
                        } else {
                            p.sendMessage(strings.NeedDebug);
                        }
                    } else if (args.length == 3) {
                        if (Main.plugin.debug) {
                            if (args[0].equalsIgnoreCase("set")) {
                                Player target = Bukkit.getPlayer(args[1]);
                                String thours = args[2];
                                boolean geht = true;
                                for (int i = 0; i < thours.length(); i++) {
                                    if (!Character.isDigit(thours.charAt(i))) {
                                        p.sendMessage(strings.needNumb);
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
                                p.sendMessage(strings.unknownCmd);
                            }
                        } else {
                            p.sendMessage(strings.NeedDebug);
                        }
                    } else {
                        p.sendMessage(strings.unknownCmd);
                    }

                } else {
                    if (args.length == 0) {
                        sendPlayTime(p,p);

                    } else if (args.length == 1) {
                        if(args[0].equalsIgnoreCase("help")) {
                            p.sendMessage(strings.ptHelp);
                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {
                                sendPlayTime(p, target);
                            } else {
                                p.sendMessage(strings.PnotOnline);
                            }
                        }
                    } else {
                        p.sendMessage(strings.syntaxPT);
                    }
                }
            } else {
                sender.sendMessage(strings.onlyPlayer);
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
            }

            if(cfg.get(target.getUniqueId() + ".minutes") != null) {
                minutes = cfg.getInt(target.getUniqueId() + ".minutes");
            }

            int nhours = hours;
            int days = 0;

            if (hours >= 24) {
                days = (hours / 24);
                nhours = hours - (days * 24);
            }


            String onemore = strings.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §feine Stunde §7und §f" + minutes + " Minuten§7.";
            String moreone = strings.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + hours + " Stunden §7und §feine Minute§7.";
            String moremore = strings.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + hours + " Stunden §7und §f" + minutes + " Minuten§7.";

            String dayone = strings.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §feinen Tag §7und §feine Stunde§7.";
            String daysone = strings.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + days + " Tage §7und §feine Stunde§7.";
            String daymore = strings.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §feinen Tag §7und §f" + nhours + " Stunden§7.";
            String daysmore = strings.prefix + "§7Die Spielzeit von §f" + target.getName()
                    + " §7beträgt §f" + days + " Tage §7und §f" + nhours + " Stunden§7.";

            if (days == 0) {
                if (hours == 1 && minutes == 1) {
                    p.sendMessage(strings.oneone);
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

        } else {
            p.sendMessage(strings.noInfo);
        }

    }
}
