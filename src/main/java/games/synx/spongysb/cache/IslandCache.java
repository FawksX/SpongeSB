package games.synx.spongysb.cache;

import com.google.common.collect.Maps;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.scheduler.Task;

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

    }

    private static final Map<UUID, Island> ISLANDS = Maps.newHashMap();

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

    public static void shutdown() {
        SpongySB.get().getLogger().info("Shutting Down IslandCache");
        save();
        SpongySB.get().getLogger().info("Island Cache Shutdown");
    }

    public static void autosave() {
        Task task = Task.builder().execute(() -> {
            SpongySB.get().getLogger().info("Saving all Island Data");
            save();
            SpongySB.get().getLogger().info("Islands Saved!");
        }).async().interval(ConfigManager.get().getConf().autosave_task_time_seconds, TimeUnit.SECONDS).submit(SpongySB.get().getPluginContainer());
    }

    private static void save() {
        for (Island island : ISLANDS.values()) {
            Island.save(island);
        }
    }
}
