package games.synx.spongysb.commands.common;

import games.synx.pscore.util.AsyncUtil;
import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.commands.common.impl.AbstractCommandCommon;
import games.synx.spongysb.events.IslandDeleteEvent;
import games.synx.spongysb.events.IslandPreDeleteEvent;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.PlayerUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class DisbandCommandCommon extends AbstractCommandCommon {

    public static void executeCommon(Player player, Island island) {

        postEvent(new IslandPreDeleteEvent(player.getUniqueId(), island));

        AsyncUtil.async(() -> {

            for(UUID pp : island.getIslandMembers()) {
                if(Sponge.getServer().getPlayer(pp).isPresent()) {
                    SPlayer islandMember = SPlayer.get(pp);
                    islandMember.removeFromIsland();
                    msg(Sponge.getServer().getPlayer(pp).get(), getMessages().disband.island_disbanded, player.getName());
                    continue;
                }

                SPlayer offPlayer = SPlayer.fetch(pp);
                offPlayer.removeFromIsland();

            }

        });


        island.setActive(false);

        for(Player aPlayer : PlayerUtil.getAllPlayersAtIsland(island)) {
            PlayerUtil.teleportToSpawn(aPlayer);
        }

        AsyncUtil.async(() -> Island.save(island));

        IslandCache.remove(island);

        postEvent(new IslandDeleteEvent(player, island.getCenterLocation(), island));

    }

}
