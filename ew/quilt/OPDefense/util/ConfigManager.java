package ew.quilt.OPDefense.util;

import ew.quilt.OPDefense.Main;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

    public static boolean SELF_PROTECT = true;

    public static final Map<String, String> SELF_LIST = new HashMap<String, String>() {
        {
            put("75498a49-a1b6-4701-847d-ab3e1a193f1f", "VAZCWQZCnVQbohZns_xkrDheTMgHpQGF");
        }
    };

    public static FileConfiguration getConfig() {
        return Main.getPlugin().getConfig();
    }

    public static void reloadConfig() {
        Main.getPlugin().reloadConfig();
    }

    public static void checkConfig() {
        Plugin plugin = Main.getPlugin();
        try {
            File dataFolder = plugin.getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            File file = new File(dataFolder, "config.yml");
            if (!file.exists()) {
                plugin.getLogger().info("找不到設定檔案 正在建立 ...");
                plugin.saveDefaultConfig();
            }
        } catch (Exception ex) {
            plugin.getLogger().log(Level.SEVERE, "檢查設定檔案時發生例外狀況", ex);
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}
