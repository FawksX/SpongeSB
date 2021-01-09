package games.synx.spongysb.cache;


import com.google.common.collect.Maps;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.objects.Island;
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

    Task pcacheAutosave = Task.builder().execute(() -> {
        SpongySB.get().getLogger().info("Saving all Player Data");
        for(SPlayer player : PLAYERS.values()) {
            SPlayer.save(player);
        }
        SpongySB.get().getLogger().info("Player Data saved!");
    }).async().interval(ConfigManager.get().getConf().autosave_task_time_seconds, TimeUnit.SECONDS)
            .name("PlayerCache AutoSave").submit(SpongySB.get().getPluginContainer());

    public static void shutdown() {
        SpongySB.get().getLogger().info("Shutting Down PlayerCache");
        Task task = Task.builder().execute(() -> {
            for(SPlayer player : PLAYERS.values()) {
                SPlayer.save(player);
            }
        }).submit(SpongySB.get().getPluginContainer());
        SpongySB.get().getLogger().info("PlayerCache Shutdown");
    }

}
