package games.synx.spongysb.util;

import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.objects.Island;

public class IslandNameUtil {

  public static boolean isIslandNameTaken(String name) {

    for(Island island : IslandCache.getAll().values()) {
      if(island.getIslandName().equals(name)) {
        return true;
      }
    }
    return false;
  }

}
