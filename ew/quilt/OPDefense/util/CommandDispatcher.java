package ew.quilt.OPDefense.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandDispatcher {

    public static void issueServerCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public static void issuePlayerCommand(Player player, String command) {
        Bukkit.dispatchCommand(player, command);
    }
}
