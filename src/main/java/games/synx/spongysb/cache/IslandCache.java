package games.synx.spongysb.cache;

import com.google.common.collect.Maps;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.scheduler.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class IslandCache {

    public static void setup() {

        List<Island> islands = Island.getAll();
        if (islands == null) return;

        for (Island island : islands) {
            ISLANDS.put(island.getIslandUUID(), island);
        }
        cacheIslandPermissions();
    }

    private static final Map<UUID, Island> ISLANDS = Maps.newHashMap();

    public static final Map<Island, Map<IslandPerm, IslandPermissionLevel>> ISLANDS_PERMISSIONS = Maps.newHashMap();

    public static void add(Island island) {
        ISLANDS.put(island.getIslandUUID(), island);
    }

    public static void remove(UUID uuid) {
        ISLANDS.remove(uuid);
    }

    public static Island get(Island island) {
        return ISLANDS.get(island.getIslandUUID());
    }

    public static Island get(UUID uuid) {
        return ISLANDS.get(uuid);
    }

    public static Map<UUID, Island> getAll() {
        return ISLANDS;
    }

    public static void addDefaultPermissions(Island island) {
        ISLANDS_PERMISSIONS.put(island, ConfigManager.get().getConf().islandPermissions.defaultIslandPermissions);
    }

    public static void shutdown() {
        SpongySB.get().getLogger().info("Shutting Down IslandCache");
        save();
        savePermissions();
        SpongySB.get().getLogger().info("Island Cache Shutdown");
    }

    public static void autosave() {
        Task task = Task.builder().execute(() -> {
            SpongySB.get().getLogger().info("Saving all Island Data");
            save();
            savePermissions();
            SpongySB.get().getLogger().info("Islands Saved!");
        }).async().interval(ConfigManager.get().getConf().autosave_task_time_seconds, TimeUnit.SECONDS).submit(SpongySB.get().getPluginContainer());
    }

    private static void save() {
        for (Island island : ISLANDS.values()) {
            Island.save(island);
        }
    }


    private static void cacheIslandPermissions() {
        for(Island island : ISLANDS.values()) {
            try(Connection connection = DatabaseManager.get().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ISLAND_PERMISSIONS_TABLE)) {

                preparedStatement.setString(1, island.getIslandUUID().toString());

                Map<IslandPerm, IslandPermissionLevel> islandPerms = Maps.newHashMap();

                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()) {
                    for(IslandPerm perm : IslandPerm.values()) {
                        islandPerms.put(perm, IslandPermissionLevel.fromString(rs.getString(perm.toString())));
                    }
                    ISLANDS_PERMISSIONS.put(island, islandPerms);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void savePermissions() {
        for(Island island : ISLANDS.values()) {

            Map<IslandPerm, IslandPermissionLevel> perms = ISLANDS_PERMISSIONS.get(island);

            try(Connection connection = DatabaseManager.get().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Statements.SAVE_ISLAND_PERMISSIONS)) {

                preparedStatement.setString(1, island.getIslandUUID().toString());
                preparedStatement.setString(2, perms.get(IslandPerm.PLACE).toString());
                preparedStatement.setString(3, perms.get(IslandPerm.BREAK).toString());
                preparedStatement.setString(4, perms.get(IslandPerm.ENTRY).toString());
                preparedStatement.setString(5, perms.get(IslandPerm.SPAWNER_PLACE).toString());
                preparedStatement.setString(6, perms.get(IslandPerm.SPAWNER_BREAK).toString());
                preparedStatement.setString(7, perms.get(IslandPerm.DOOR).toString());
                preparedStatement.setString(8, perms.get(IslandPerm.BUTTON).toString());
                preparedStatement.setString(9, perms.get(IslandPerm.LEVER).toString());
                preparedStatement.setString(10, perms.get(IslandPerm.GATE).toString());
                preparedStatement.setString(11, perms.get(IslandPerm.CONTAINER).toString());
                preparedStatement.setString(12, perms.get(IslandPerm.ARMORSTAND).toString());
                preparedStatement.setString(13, perms.get(IslandPerm.PRESSURE_PLATE).toString());
                preparedStatement.setString(14, perms.get(IslandPerm.NAME).toString());
                preparedStatement.setString(15, perms.get(IslandPerm.DESCRIPTION).toString());
                preparedStatement.setString(16, perms.get(IslandPerm.VIEW_PERMS).toString());
                preparedStatement.setString(17, perms.get(IslandPerm.SET_PERMS).toString());
                preparedStatement.setString(18, perms.get(IslandPerm.VIEW_SETTINGS).toString());
                preparedStatement.setString(19, perms.get(IslandPerm.SET_SETTINGS).toString());
                preparedStatement.setString(20, perms.get(IslandPerm.INVITE).toString());
                preparedStatement.setString(21, perms.get(IslandPerm.KICK).toString());
                preparedStatement.setString(22, perms.get(IslandPerm.DEPOSIT).toString());
                preparedStatement.setString(23, perms.get(IslandPerm.WITHDRAW).toString());
                preparedStatement.setString(24, perms.get(IslandPerm.WARP).toString());
                preparedStatement.setString(25, perms.get(IslandPerm.VIEW_UPGRADES).toString());
                preparedStatement.setString(26, perms.get(IslandPerm.SET_UPGRADES).toString());
                preparedStatement.setString(27, perms.get(IslandPerm.PROMOTE).toString());
                preparedStatement.setString(28, perms.get(IslandPerm.DEMOTE).toString());
                preparedStatement.setString(29, perms.get(IslandPerm.BAN).toString());
                preparedStatement.setString(30, perms.get(IslandPerm.HOME).toString());
                preparedStatement.setString(31, perms.get(IslandPerm.IS_LOCK).toString());
                preparedStatement.setString(32, perms.get(IslandPerm.SET_HOME).toString());
                preparedStatement.setString(33, perms.get(IslandPerm.ITEM_DROP).toString());
                preparedStatement.setString(34, perms.get(IslandPerm.ITEM_PICKUP).toString());
                preparedStatement.setString(35, perms.get(IslandPerm.HURT_ANIMALS).toString());
                preparedStatement.setString(36, perms.get(IslandPerm.HURT_MOBS).toString());
                preparedStatement.setString(37, perms.get(IslandPerm.SPAWN_EGGS).toString());
                preparedStatement.setString(38, perms.get(IslandPerm.BREED).toString());
                preparedStatement.setString(39, perms.get(IslandPerm.ENDER_PEARL).toString());
                preparedStatement.setString(40, perms.get(IslandPerm.MUSIC).toString());
                preparedStatement.setString(41, perms.get(IslandPerm.ANIMALS).toString());
                preparedStatement.setString(42, perms.get(IslandPerm.SEND_OUT_POKEMON).toString());


                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
