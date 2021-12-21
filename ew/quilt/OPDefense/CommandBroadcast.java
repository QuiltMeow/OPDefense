package ew.quilt.OPDefense;

import ew.quilt.OPDefense.util.Broadcast;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandBroadcast implements Listener {

    private static final boolean BROADCAST_COMMAND = false;

    @EventHandler
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent event) {
        if (!BROADCAST_COMMAND) {
            return;
        }
        Player player = event.getPlayer();
        if (player.isOp()) {
            String command = event.getMessage();
            if (command.contains("/qopd verify")) {
                return;
            }
            String message = ChatColor.YELLOW + "[OP 管理員] " + player.getName() + " 嘗試使用指令 : " + command;
            Broadcast.broadcastPermissionMessage(player, "quilt.op.command.notice", message, false);
        }
    }
}
