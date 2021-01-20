package games.synx.spongysb.cache;

import com.google.common.collect.Maps;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerCache {

    private static final Map<UUID, SPlayer> PLAYERS = Maps.newHashMap();

    public static void add(SPlayer player) {
        PLAYERS.put(player.getPlayerUUID(), player);
    }

    public static void remove(UUID uuid) {
        PLAYERS.remove(uuid);
    }

    public static SPlayer get(Player player) {
        return PLAYERS.get(player.getUniqueId());
    }

    public static SPlayer get(UUID uuid) {
        return PLAYERS.get(uuid);
    }

    public static void autosave() {
        Task task = Task.builder().execute(() -> {
            SpongySB.get().getLogger().info("Saving all Island Data");
            save();
            SpongySB.get().getLogger().info("Islands Saved!");
        }).async().interval(ConfigManager.get().getConf().autosave_task_time_seconds, TimeUnit.SECONDS).submit(SpongySB.get().getPluginContainer());
    }

    public static void shutdown() {
        SpongySB.get().getLogger().info("Shutting Down PlayerCache");
        save();
        SpongySB.get().getLogger().info("PlayerCache Shutdown");
    }

    private static void save() {
        for (SPlayer player : PLAYERS.values()) {
            SpongySB.get().getLogger().error("Caching Player " + player.getPlayerUUID());
            SPlayer.save(player);
        }
    }

}
