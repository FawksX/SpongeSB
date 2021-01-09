package games.synx.spongysb.util;

import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.objects.Island;

import java.util.UUID;

public class IslandUtil {

    public static boolean isIslandNameTaken(String name) {

        for(Island island : IslandCache.getAll().values()) {
            if(island.getIslandName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static UUID generateUniqueIslandUUID() {

        UUID uuid = UUID.randomUUID();
        for(Island island : IslandCache.getAll().values()) {
            if(island.getIslandUUID().toString().equals(uuid.toString())) {
                return generateUniqueIslandUUID();
            }
        }
        return uuid;

    }

}
