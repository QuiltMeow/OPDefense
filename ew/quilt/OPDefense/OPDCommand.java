package ew.quilt.OPDefense;

import ew.quilt.OPDefense.util.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class OPDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length <= 0) {
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "verify": {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "該指令必須在遊戲內由玩家使用");
                    return false;
                }
                if (args.length < 2) {
                    return false;
                }
                Player player = (Player) sender;
                String password = OPSecurity.getVerifyPassword(player);
                if (password == null) {
                    return false;
                }
                if (!args[1].equals(password)) {
                    return false;
                }
                CommandProtect.getVerifyPlayer().add(sender.getName());
                sender.sendMessage(ChatColor.GREEN + "驗證成功 !");
                return true;
            }
            case "opreload": {
                if (!(sender instanceof ConsoleCommandSender)) {
                    sender.sendMessage(ChatColor.RED + "權限不足 無法使用該指令 !");
                    return false;
                }
                ConfigManager.reloadConfig();
                OPSecurity.loadOPList();
                sender.sendMessage(ChatColor.GREEN + "OP 列表重載完成");
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
