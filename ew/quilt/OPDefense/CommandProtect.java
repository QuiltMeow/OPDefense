package ew.quilt.OPDefense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandProtect implements Listener {

    private static final Set<String> VERIFY_PLAYER = new HashSet<>();

    private static final List<String> BAD_COMMAND_PATTERN_CONTAIN_BAN = new ArrayList<>(Arrays.asList(new String[]{
        "/groupmanager:",
        "/permissionsex:",
        "/luckperms:",
        "/ezprotector:",
        "/spigot:",
        "/paper:"
    }));

    private static final List<String> BAD_COMMAND_PATTERN_EQUAL_BAN = new ArrayList<>(Arrays.asList(new String[]{
        "/bukkit:about",
        "/bukkit:ver",
        "/bukkit:version",
        "/bukkit:pl",
        "/bukkit:plugins",
        "/powertool",
        "/epowertool",
        "/essentials:powertool",
        "/essentials:epowertool",
        "/essentials:pt",
        "/essentials:ept",
        "/ezprotector",
        "/bukkit:reload",
        "/bukkit:rl",
        "/icanhasbukkit",
        "/minecraft:help"
    }));

    private static final List<String> BAD_COMMAND_PATTERN_CONTAIN_CANCEL = new ArrayList<>(Arrays.asList(new String[]{
        "/bukkit:",
        "/essentials:",
        "/minecraft:"
    }));

    private static final List<String> BAD_COMMAND_PATTERN_EQUAL_CANCEL = new ArrayList<>(Arrays.asList(new String[]{
        "/manuadd",
        "/manudel",
        "/manuaddsub",
        "/manudelsub",
        "/mangadd",
        "/mangdel",
        "/manuaddp",
        "/manudelp",
        "/manuclearp",
        "/manulistp",
        "/manucheckp",
        "/mangaddp",
        "/mangdelp",
        "/mangclearp",
        "/manglistp",
        "/mangcheckp",
        "/mangaddi",
        "/mangdeli",
        "/manuaddv",
        "/manudelv",
        "/manulistv",
        "/manucheckv",
        "/mangaddv",
        "/mangdelv",
        "/manglistv",
        "/mangcheckv",
        "/manwhois",
        "/tempadd",
        "/tempdel",
        "/templist",
        "/tempdelall",
        "/mansave",
        "/manload",
        "/listgroups",
        "/manpromote",
        "/mandemote",
        "/mantogglevalidate",
        "/mantogglesave",
        "/manworld",
        "/manselect",
        "/manclear",
        "/mancheckw",
        "/op",
        "/deop",
        "/minecraft:op",
        "/minecraft:deop",
        "/pex",
        "/promote",
        "/demote",
        "/luckperms",
        "/lp",
        "/perm",
        "/perms",
        "/permission",
        "/permissions",
        "/pardon",
        "/minecraft:pardon",
        "/pt",
        "/ept",
        "/ezp",
        "/reload",
        "/spigot",
        "/paper"
    }));

    private static final List<String> COMMAND_PATTERN_EQUAL_REQUIRE_PERMISSION = new ArrayList<>(Arrays.asList(new String[]{
        "/me",
        "/minecraft:me"
    }));

    private static final List<String> COMMAND_PATTERN_CONTAIN_REQUIRE_VERIFY = new ArrayList<>(Arrays.asList(new String[]{
        "/commandnpc:",
        "/serversigns:",
        "/multiworld",
        "/mw-v",
        "/cmi:",
        "/aac:",
        "/autosaveworld",
        "/plugman",
        "/powernbt",
        "/react:",
        "/touchhologram",
        "/touchscreenholograms:",
        "/worldborder:",
        "/worldedit",
        "//",
        "/worldguard",
        "/voxelsniper:",
        "/clearlag:",
        "/quiltplugin:",
        "/playerpoints:"
    }));

    private static final List<String> COMMAND_PATTERN_EQUAL_REQUIRE_VERIFY = new ArrayList<>(Arrays.asList(new String[]{
        "/tpall",
        "/commandnpc",
        "/serversigns",
        "/serversignsremote",
        "/svs",
        "/svsr",
        "/mv",
        "/mvcreate",
        "/mvc",
        "/mvimport",
        "/mvim",
        "/mvremove",
        "/mvdelete",
        "/mvunload",
        "/mvmodify",
        "/mvmset",
        "/mvmadd",
        "/mvmremove",
        "/mvmclear",
        "/mvm",
        // "/mvtp",
        "/mvlist",
        "/mvl",
        "/mvsetspawn",
        "/mvset",
        "/mvss",
        "/mvspawn",
        "/mvs",
        "/mvcoord",
        "/mvc",
        "/mvwho",
        "/mvw",
        "/mvreload",
        "/mvr",
        "/mvpurge",
        // "/mvconfirm",
        "/mvinfo",
        "/mvi",
        "/mvenv",
        "/mvv",
        "/mvversion",
        "/mvco",
        "/mvh",
        "/mvsearch",
        "/mvhelp",
        "/mvdebug",
        "/mvgenerators",
        "/mvgens",
        "/mvload",
        "/mvregen",
        "/mvscript",
        "/mvclone",
        "/mvsilent",
        "/mvgamerule",
        "/mvrule",
        "/mvgamerules",
        "/mvrules",
        "/bungee",
        "/about",
        "/ver",
        "/version",
        "/?",
        "/pl",
        "/plugins",
        "/help",
        "/mw",
        "/goto",
        "/eco",
        "/eeco",
        "/economy",
        "/eeconomy",
        "/aac",
        "/aacunban",
        "/aacadmin",
        "/aackick",
        "/aacban",
        "/aacreload",
        "/aacdebug",
        "/aacverbose",
        "/aacmessage",
        "/aacstaffnotify",
        "/aacrestorechunk",
        "/asw",
        "/autosave",
        "/save",
        "/autobackup",
        "/backup",
        "/autopurge",
        "/purge",
        "/pnbt",
        "/nbt",
        "/pnbt.",
        "/nbt.",
        "/react",
        "/re",
        "/ract",
        "/rai",
        "/react-ai",
        "/touch",
        "/th",
        "/wborder",
        "/wb",
        "/we",
        "/biomeinfo",
        "/biomelist",
        "/biomels",
        "/chunkinfo",
        "/listchunks",
        "/delchunks",
        "/clearclipboard",
        "/forestgen",
        "/pumpkins",
        "/unstuck",
        "/!",
        "/ascend",
        "/asc",
        "/descend",
        "/desc",
        "/ceil",
        "/thru",
        "/jumpto",
        "/j",
        "/up",
        "/.s",
        "/cs",
        "/toggleeditwand",
        "/snapshot",
        "/snap",
        "/gmask",
        "/toggleplace",
        "/searchitem",
        "/undo",
        "/redo",
        "/clearhistory",
        "/;",
        "/restore",
        "/,",
        "/superpickaxe",
        "/pickaxe",
        "/sp",
        "/tool",
        "/mat",
        "/material",
        "/range",
        "/size",
        "/mask",
        "/none",
        "/tree",
        "/repl",
        "/cycler",
        "/floodfill",
        "/flood",
        "/brush",
        "/br",
        "/deltree",
        "/farwand",
        "/lrbuild",
        "/info",
        "/fixlava",
        "/fixwater",
        "/removeabove",
        "/removebelow",
        "/removenear",
        "/replacenear",
        "/snow",
        "/thaw",
        "/green",
        "/ex",
        "/ext",
        "/extinguish",
        "/butcher",
        "/remove",
        "/rem",
        "/rement",
        "/locate",
        "/stack",
        "/stopfire",
        "/allowfire",
        "/halt-activity",
        "/region",
        "/paint",
        "/goto",
        "/u",
        "/uu",
        "/d",
        "/p",
        "/perf",
        "/performer",
        "/vs",
        "/vc",
        "/vh",
        "/vi",
        "/vr",
        "/vl",
        "/vir",
        // "/v",
        "/b",
        "/btool",
        "/vchunk",
        "/timings",
        "/lag",
        "/entities",
        // "/tps",
        "/lagg",
        "/url",
        "/ip",
        "/qjs",
        "/whois",
        // "/seen",
        "/quilt",
        "/qlt",
        "/qp",
        "/points"
    }));

    private static final List<String> COMMAND_EXTRA_PATTERN_CONTAIN_REQUIRE_VERIFY = new ArrayList<>(Arrays.asList(new String[]{
        "/cmi money give",
        "/cmi money set",
        "/cmi money take",
        "/money give",
        "/money set",
        "/money take",
        "/cmi tpall",
        "/cmi attachcommand",
        "/cmi sudo"
    }));

    private static final List<String> COMMAND_EXTRA_PATTERN_EQUAL_CANCEL_REQUIRE_VERIFY = new ArrayList<>(Arrays.asList(new String[]{
        "/bukkit:help",
        "/bukkit:?"
    }));

    private static final List<String> COMMAND_EXTRA_PATTERN_CONTAIN_REQUIRE_VERIFY_ALLOW = new ArrayList<>(Arrays.asList(new String[]{
        "/mv tp",
        "/mv confirm",
        "/points me"
    }));

    public static Set<String> getVerifyPlayer() {
        return VERIFY_PLAYER;
    }

    public static void sendNoVerify(Player player) {
        player.sendMessage(ChatColor.RED + "很抱歉 該指令必須驗證後才可使用 !");
    }

    public static void sendNoPermission(Player player) {
        player.sendMessage(ChatColor.RED + "權限不足 無法使用該指令 !");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String playerCommand = event.getMessage().toLowerCase();
        for (String command : COMMAND_EXTRA_PATTERN_CONTAIN_REQUIRE_VERIFY) {
            if (playerCommand.contains(command)) {
                if (!VERIFY_PLAYER.contains(name)) {
                    sendNoVerify(player);
                    event.setCancelled(true);
                }
                return;
            }
        }

        String pattern = playerCommand.split(" ")[0];
        for (String command : BAD_COMMAND_PATTERN_CONTAIN_BAN) {
            if (pattern.contains(command)) {
                BanProcess.processBanCommand(player.getName(), "使用非法指令");
                event.setCancelled(true);
                return;
            }
        }
        for (String command : BAD_COMMAND_PATTERN_EQUAL_BAN) {
            if (pattern.equalsIgnoreCase(command)) {
                BanProcess.processBanCommand(player.getName(), "使用非法指令");
                event.setCancelled(true);
                return;
            }
        }
        for (String command : BAD_COMMAND_PATTERN_CONTAIN_CANCEL) {
            if (pattern.contains(command)) {
                for (String verify : COMMAND_EXTRA_PATTERN_EQUAL_CANCEL_REQUIRE_VERIFY) {
                    if (playerCommand.equalsIgnoreCase(verify)) {
                        if (!VERIFY_PLAYER.contains(name)) {
                            sendNoVerify(player);
                            event.setCancelled(true);
                        }
                        return;
                    }
                }
                event.setCancelled(true);
                return;
            }
        }
        for (String command : BAD_COMMAND_PATTERN_EQUAL_CANCEL) {
            if (pattern.equalsIgnoreCase(command)) {
                event.setCancelled(true);
                return;
            }
        }
        for (String command : COMMAND_PATTERN_EQUAL_REQUIRE_PERMISSION) {
            if (pattern.equalsIgnoreCase(command)) {
                if (!player.hasPermission("quilt.access.limit.command")) {
                    sendNoPermission(player);
                    event.setCancelled(true);
                }
                return;
            }
        }
        for (String command : COMMAND_PATTERN_CONTAIN_REQUIRE_VERIFY) {
            if (pattern.contains(command)) {
                if (!VERIFY_PLAYER.contains(name)) {
                    sendNoVerify(player);
                    event.setCancelled(true);
                }
                return;
            }
        }
        for (String command : COMMAND_PATTERN_EQUAL_REQUIRE_VERIFY) {
            if (pattern.equalsIgnoreCase(command)) {
                for (String allow : COMMAND_EXTRA_PATTERN_CONTAIN_REQUIRE_VERIFY_ALLOW) {
                    if (playerCommand.contains(allow)) {
                        return;
                    }
                }
                if (!VERIFY_PLAYER.contains(name)) {
                    sendNoVerify(player);
                    event.setCancelled(true);
                }
                return;
            }
        }
    }

    public static void removeBlock(Block block) {
        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(Material.AIR);
            }
        }.runTaskLater(Main.getPlugin(), 2);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityChangeBlock(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (block != null) {
            BlockState state = block.getState();
            if (state != null) {
                if (state instanceof CommandBlock) {
                    CommandBlock cb = (CommandBlock) state;
                    String blockCommand = cb.getCommand().toLowerCase();
                    if (!blockCommand.startsWith("/")) {
                        blockCommand = "/" + blockCommand;
                    }
                    for (String command : COMMAND_EXTRA_PATTERN_CONTAIN_REQUIRE_VERIFY) {
                        if (blockCommand.contains(command)) {
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }

                    String pattern = blockCommand.split(" ")[0];
                    for (String command : BAD_COMMAND_PATTERN_CONTAIN_BAN) {
                        if (pattern.contains(command)) {
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }
                    for (String command : BAD_COMMAND_PATTERN_EQUAL_BAN) {
                        if (pattern.equalsIgnoreCase(command)) {
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }
                    for (String command : BAD_COMMAND_PATTERN_CONTAIN_CANCEL) {
                        if (pattern.contains(command)) {
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }
                    for (String command : BAD_COMMAND_PATTERN_EQUAL_CANCEL) {
                        if (pattern.equalsIgnoreCase(command)) {
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }
                    for (String command : COMMAND_PATTERN_EQUAL_REQUIRE_PERMISSION) {
                        if (pattern.equalsIgnoreCase(command)) {
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }
                    for (String command : COMMAND_PATTERN_CONTAIN_REQUIRE_VERIFY) {
                        if (pattern.contains(command)) {
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }
                    for (String command : COMMAND_PATTERN_EQUAL_REQUIRE_VERIFY) {
                        if (pattern.equalsIgnoreCase(command)) {
                            for (String allow : COMMAND_EXTRA_PATTERN_CONTAIN_REQUIRE_VERIFY_ALLOW) {
                                if (blockCommand.contains(allow)) {
                                    return;
                                }
                            }
                            removeBlock(block);
                            event.setNewCurrent(0);
                            return;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String name = event.getPlayer().getName();
        VERIFY_PLAYER.remove(name);
    }
}
