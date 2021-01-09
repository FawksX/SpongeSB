package games.synx.spongysb.util;

import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.objects.Island;

import java.util.UUID;

public class UUIDUtil {

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
