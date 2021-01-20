package games.synx.spongysb.cache;

import com.google.common.collect.ArrayListMultimap;

import games.synx.pscore.util.AsyncUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BanCache {

    // USER UUID : ISLAND UUID
    private static final ArrayListMultimap<UUID, UUID> BANS = ArrayListMultimap.create();

    public static void add(SPlayer player, Island island) {
        add(player.getPlayerUUID(), island.getIslandUUID());
    }

    public static void add(UUID player, UUID island) {
        BANS.put(player, island);
    }

    public static void remove(Player player, Island island) {
        remove(player.getUniqueId(), island.getIslandUUID());
    }

    public static void remove(UUID player, UUID island) {
        BANS.remove(player, island);
    }

    public static boolean isBanned(Player player, Island island) {
        return isBanned(player.getUniqueId(), island.getIslandUUID());
    }

    public static boolean isBanned(UUID player, UUID island) {
        return BANS.containsEntry(player, island);
    }


    public static void autosave() {
        Task task = Task.builder().execute(() -> {
            SpongySB.get().getLogger().info("Saving all Bans Data");
            save();
            SpongySB.get().getLogger().info("Bans Saved!");
        }).async().interval(ConfigManager.get().getConf().autosave_task_time_seconds, TimeUnit.SECONDS).submit(SpongySB.get().getPluginContainer());
    }

    public static void shutdown() {
        SpongySB.get().getLogger().info("Shutting Down BansCache");
        save();
        SpongySB.get().getLogger().info("BansCache Shutdown");
    }

    private static void save() {
        for (UUID player : BANS.keys()) {
            for(UUID island : BANS.keySet()) {
                AsyncUtil.async(() -> saveBan(player, island));
            }
        }
    }

    private static void saveBan(UUID player, UUID island) {
        try(Connection connection = DatabaseManager.get().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.INSERT_BAN)) {

            preparedStatement.setString(1, player.toString());
            preparedStatement.setString(2, island.toString());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
