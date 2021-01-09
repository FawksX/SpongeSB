package games.synx.spongysb.cache;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class CoopCache {

    // Island : PlayerUUIDs
    public static final Multimap<Island, UUID> COOPS = ArrayListMultimap.create();

    public static void add(Island island, Player player) {
        COOPS.put(island, player.getUniqueId());
    }

    public static void add(Island island, UUID player) {
        COOPS.put(island, player);
    }

    public static void remove(Island island, UUID playerUUID) {
        COOPS.remove(island, playerUUID);
    }

    public static void removeIfPresent(UUID playerUUID) {
        if(CoopCache.getAll().containsValue(playerUUID)) {
            COOPS.removeAll(playerUUID);
        }
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


}
