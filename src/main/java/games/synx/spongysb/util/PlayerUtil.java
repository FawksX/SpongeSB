package games.synx.spongysb.util;

import com.google.common.collect.Lists;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

public class PlayerUtil {

    public static List<Player> getAllPlayersAtIsland(Island island) {
        List<Player> playersAtIsland = Lists.newArrayList();

        for(Player player: Sponge.getServer().getOnlinePlayers()) {
            if(isAtIsland(player, island)) {
                playersAtIsland.add(player);
            }
        }
        return playersAtIsland;
    }

    public static boolean isAtIsland(Player player, Island island) {
        return Island.getIslandAt(player.getLocation()).getCenterLocation() == island.getCenterLocation();
    }

}
