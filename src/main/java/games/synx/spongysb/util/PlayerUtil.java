package games.synx.spongysb.util;

import com.google.common.collect.Lists;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.Island;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

public class PlayerUtil {

    public static List<Player> getAllPlayersAtIsland(Island island) {
        List<Player> playersAtIsland = Lists.newArrayList();

        for(Player player: Sponge.getServer().getOnlinePlayers()) {
            if(Island.getIslandAt(player.getLocation()) == null) continue;

            if(isAtIsland(player, island)) {
                playersAtIsland.add(player);
            }
        }
        return playersAtIsland;
    }

    public static boolean isAtIsland(Player player, Island island) {
        return Island.getIslandAt(player.getLocation()).getCenterLocation() == island.getCenterLocation();
    }

    public static void teleportToSpawn(EntityPlayerMP player) {
        teleportToSpawn((Player) player);
    }

    public static void teleportToSpawn(Player player) {
        player.setLocationSafely(WorldManager.get().getServerSpawn());
    }

}
