package games.synx.spongysb.events;

import games.synx.spongysb.events.base.IslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * Called whenever an island is reset, after {@link IslandPreDeleteEvent}
 *
 * EVENT FIRED FROM:
 * - tbd..
 */
public class IslandResetEvent extends IslandEvent {

    private final Location<World> location;
    private final Player player;

    public IslandResetEvent(Player player, Location<World> oldLocation, Island island, Cause cause) {
        super(island, cause);

        this.player = player;
        this.location = oldLocation;
    }

    public Player getPlayer() {
        return player;
    }

    public Location<World> getLocation() {
        return location;
    }

}
