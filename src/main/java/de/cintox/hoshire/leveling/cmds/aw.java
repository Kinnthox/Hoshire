package de.cintox.hoshire.leveling.cmds;

import de.cintox.hoshire.leveling.util.xpbar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class aw implements CommandExecutor {

    xpbar bar = new xpbar();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("way")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length == 0) {
                    bar.showbar(p);
                }
            }
        }
        return false;
    }
}
