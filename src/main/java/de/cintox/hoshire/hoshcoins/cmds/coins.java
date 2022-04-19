package de.cintox.hoshire.hoshcoins.cmds;

import de.cintox.hoshire.Main;
import de.cintox.hoshire.hoshcoins.util.saveC;
import de.cintox.hoshire.util.strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class coins implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("coins")) {

            if (sender instanceof Player) {
                Player p = (Player) sender;

                // /coins
                if (args.length == 0) {
                    p.sendMessage(Main.plugin.prefix + "§7Du hast aktuell §f" + saveC.getCoins(p.getUniqueId()) + " §6Hoshcoins§7.");

                    // /coins help
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("help")) {
                        if (p.isOp()) p.sendMessage(strings.helpOP);
                        else p.sendMessage(strings.help);

                    } else {
                        if(p.isOp()) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if(target != null) {
                                p.sendMessage(Main.plugin.prefix + "§7Spieler §f" + args[0]
                                        + " §7besitzt §f" + saveC.getCoins(target.getUniqueId()) + " §6Hoshcoins§7.");
                            } else {
                                p.sendMessage(strings.Error);
                            }
                        } else {
                            p.sendMessage(strings.syntaxError);
                        }
                    }

                    // /coins get <Player>
                }
                    // /coins set Kinnthox 100
                    // /coins add Kinnthox 100
                    // /coins remove Kinnthox 100
                 else if (args.length == 3) {
                    if (p.isOp()) {

                        Player target = Bukkit.getPlayer(args[1]);
                        int coins = Integer.parseInt(args[2]);

                        if (target == null) {
                            p.sendMessage(strings.Error);
                            return false;
                        }

                        // /coins set Kinnthox 100
                        if (args[0].equalsIgnoreCase("set")) {

                            if (saveC.setCoins(target.getUniqueId(), coins)) {
                                p.sendMessage(Main.plugin.prefix + "§7Die §6Hoshcoins §7von §f" + target.getName()
                                        + "§7 wurden auf §f" + coins + " §6Hoshcoins §7gesetzt.");
                            } else {
                                p.sendMessage(strings.Error);
                            }

                            // /coins add Kinnthox 100
                        } else if (args[0].equalsIgnoreCase("add")) {

                            if (saveC.addCoins(target.getUniqueId(), coins)) {
                                p.sendMessage(Main.plugin.prefix + "§7Dem Konto von §f" + target.getName()
                                        + "§7 wurden §f" + coins + " §6Hoshcoins §7gutgeschrieben.");
                            } else {
                                p.sendMessage(strings.Error);
                            }

                            // /coins remove Kinnthox 100
                        } else if (args[0].equalsIgnoreCase("remove")) {

                            if (coins <= saveC.getCoins(target.getUniqueId())) {
                                if (saveC.removeCoins(target.getUniqueId(), coins)) {
                                    p.sendMessage(Main.plugin.prefix + "§7Vom Konto von §f" + target.getName()
                                            + "§7 wurden §f" + coins + " §6Hoshcoins §7entfernt.");
                                } else {
                                    p.sendMessage(strings.Error);
                                }
                            } else {
                                p.sendMessage(strings.higherCoins);
                            }

                        }

                    } else {
                        p.sendMessage(strings.syntaxError);
                    }


                } else {
                    p.sendMessage(strings.syntaxError);
                }

            }

        }

        return false;
    }
}
