package ew.quilt.OPDefense;

import ew.quilt.OPDefense.util.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        getLogger().info("Quilt OP Defense 正在啟用 ...");
        plugin = this;
        ConfigManager.checkConfig();

        OPSecurity.loadOPList();
        getCommand("qopd").setExecutor(new OPDCommand());

        getServer().getPluginManager().registerEvents(new OPSecurity(), this);
        OPSecurity.registerOPDetect();

        getServer().getPluginManager().registerEvents(new CommandProtect(), this);
        TabProtect.registerTabProtect();

        getServer().getPluginManager().registerEvents(new CommandBroadcast(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("[關閉] Quilt OP Defense 關閉中 ...");
        if (ConfigManager.SELF_PROTECT) {
            getLogger().info("插件已被關閉 自我保護機制已啟動 ...");
            getServer().shutdown();
        }

        plugin = null;
        super.onDisable();
    }

    public static Main getPlugin() {
        return plugin;
    }
}
