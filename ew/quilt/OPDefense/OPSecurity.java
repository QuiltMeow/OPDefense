package ew.quilt.OPDefense;

import ew.quilt.OPDefense.util.ConfigManager;
import ew.quilt.OPDefense.util.TimerUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

public class OPSecurity implements Listener {

    private static final int CHECK_INTERVAL = 5;
    private static final int SELF_PROTECT_INTERVAL = 30;

    private static Map<String, String> opList;
    private static long LAST_CHECK_TIME;

    private static boolean LOADING = true;

    public static void loadOPList() {
        LOADING = true;
        opList = new HashMap<>();

        opList.putAll(ConfigManager.SELF_LIST);
        ConfigurationSection opSection = ConfigManager.getConfig().getConfigurationSection​("OPList");
        List<ConfigurationSection> opData = getSectionList(opSection);
        for (ConfigurationSection section : opData) {
            String uuid = section.getString("uuid");
            if (uuid == null) {
                continue;
            }
            String key = section.getString("key");
            opList.put(uuid, key);
        }
        LOADING = false;
    }

    public static List<ConfigurationSection> getSectionList(ConfigurationSection source) {
        List<ConfigurationSection> node = new ArrayList<>();
        for (String key : source.getKeys(false)) {
            if (source.isConfigurationSection(key)) {
                node.add(source.getConfigurationSection(key));
            }
        }
        return node;
    }

    public static void registerOPDetect() {
        LAST_CHECK_TIME = System.currentTimeMillis();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            if (!LOADING) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (checkIllegalOP(player)) {
                        player.setOp(false);
                        BanProcess.processBanCommand(player.getName());
                    }
                }
            }
            LAST_CHECK_TIME = System.currentTimeMillis();
        }, TimerUtil.secondToTick(CHECK_INTERVAL), TimerUtil.secondToTick(CHECK_INTERVAL));
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (checkIllegalOP(player)) {
            player.setOp(false);
            BanProcess.processBanCommand(player.getName());
        }
        if (ConfigManager.SELF_PROTECT && LAST_CHECK_TIME + SELF_PROTECT_INTERVAL * 1000 < System.currentTimeMillis()) {
            Plugin plugin = Main.getPlugin();
            plugin.getLogger().warning("計時器被關閉 自我保護機制已啟動 ...");
            plugin.getServer().shutdown();
        }
    }

    public static boolean checkIllegalOP(Player player) {
        return !opList.containsKey(player.getUniqueId().toString()) && player.isOp();
    }

    public static String getVerifyPassword(Player player) {
        return opList.get(player.getUniqueId().toString());
    }
}
