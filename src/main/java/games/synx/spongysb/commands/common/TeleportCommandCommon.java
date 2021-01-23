package games.synx.spongysb.commands.common;

import games.synx.spongysb.commands.common.impl.AbstractCommandCommon;
import games.synx.spongysb.events.IslandEnterEvent;
import games.synx.spongysb.events.IslandPreTeleportEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class TeleportCommandCommon extends AbstractCommandCommon {

    public static void executeCommon(Island island, Player player, IslandPreTeleportEvent.Reason reason) {

        postEvent(new IslandPreTeleportEvent(island, player, reason, island.getHomeLocation()));

        Location<World> teleportLocation = island.getHomeLocation();

        if(player.setLocationSafely(teleportLocation)) {
            postEvent(new IslandEnterEvent(player.getUniqueId(), island, teleportLocation));
            player.setLocationSafely(teleportLocation);
            msg(player, getMessages().teleport.teleporting_to_your_island);

        } else {
            if(player.setLocationSafely(island.getCenterLocation())) {
                postEvent(new IslandEnterEvent(player.getUniqueId(), island, teleportLocation));
                player.setLocationSafely(island.getCenterLocation());
                msg(player, getMessages().teleport.teleporting_to_default_home_location);
            } else {
                msg(player, getMessages().teleport.could_not_teleport_to_island);
            }
        }

    }

}
