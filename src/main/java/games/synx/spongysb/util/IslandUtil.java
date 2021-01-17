package games.synx.spongysb.util;

import games.synx.pscore.util.CauseUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldBorder;

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

    public static void changeBorder(Player player, Location<World> location) {

        Cause cause = CauseUtil.getCause(SpongySB.get().getPluginContainer());
        if(!GridManager.get().inWorld(location)) {
            player.setWorldBorder(null, cause);
            return;
        }
        Island island = Island.getIslandAt(location);
        Location<World> centerLoc = island.getCenterLocation();

        player.setWorldBorder(WorldBorder.builder().center(centerLoc.getX(), centerLoc.getZ()).diameter(island.getSize()).build(), cause);

    }

}
