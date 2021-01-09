package games.synx.spongysb.cache;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CoopCache {

    // Island : PlayerUUIDs
    public static final Multimap<Island, UUID> COOPS = ArrayListMultimap.create();

    public static void setup() {

        try (Connection connection = DatabaseManager.get().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ALL_COOPS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                COOPS.put(Island.fetch(UUID.fromString(rs.getString("island_uuid"))), UUID.fromString(rs.getString("player_uuid")));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void add(Island island, Player player) {
        COOPS.put(island, player.getUniqueId());
    }

    public static void add(Island island, UUID player) {
        COOPS.put(island, player);
    }

    public static void remove(Island island, UUID playerUUID) {
        COOPS.remove(island, playerUUID);
    }

    public static boolean isCoop(Island island, Player player) {
        return isCoop(island, player.getUniqueId());
    }

    public static boolean isCoop(Island island, UUID player) {
        return COOPS.containsEntry(island, player);
    }

    public static Multimap<Island, UUID> getAll() {
        return COOPS;
    }

    public static void shutdown() {
        SpongySB.get().getLogger().info("Shutting Down CoopCache");
        Task task = Task.builder().execute(() -> {
            for(Island island : COOPS.keys()) {
                COOPS.get(island).forEach(uuid -> save(island, uuid));
            }
        }).submit(SpongySB.get().getPluginContainer());
        SpongySB.get().getLogger().info("CoopCache Shutdown");
    }

    public static void autosave() {
        Task task = Task.builder().execute(() -> {
            SpongySB.get().getLogger().info("Saving all Coop Data");
            for(Island island : COOPS.keys()) {
                COOPS.get(island).forEach(uuid -> save(island, uuid));
            }
            SpongySB.get().getLogger().info("Coops Saved!");
        }).async().interval(ConfigManager.get().getConf().autosave_task_time_seconds, TimeUnit.SECONDS).submit(SpongySB.get().getPluginContainer());
    }


    private static void save(Island island, UUID player) {

        clearDB();
        try (Connection connection = DatabaseManager.get().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Statements.SAVE_ALL_COOPS)) {
            preparedStatement.setString(1, player.toString());
            preparedStatement.setString(2, island.getIslandUUID().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void clearDB() {
        try (Connection connection = DatabaseManager.get().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Statements.TRUNCATE_COOPS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
