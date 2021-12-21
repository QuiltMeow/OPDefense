package ew.quilt.OPDefense;

import ew.quilt.OPDefense.util.CommandDispatcher;
import ew.quilt.OPDefense.util.ConfigManager;
import org.bukkit.ChatColor;

public class BanProcess {

    public static void processBanCommand(String name) {
        String reason = ConfigManager.getConfig().get("BanReason").toString();
        processBanCommand(name, reason);
    }

    public static void processBanCommand(String name, String reason) {
        CommandDispatcher.issueServerCommand(ChatColor.translateAlternateColorCodes('&', "ban-ip " + name + " " + reason));
        CommandDispatcher.issueServerCommand(ChatColor.translateAlternateColorCodes('&', "ban " + name + " " + reason));
    }
}
