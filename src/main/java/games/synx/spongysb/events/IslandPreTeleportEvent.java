package games.synx.spongysb.events;

import games.synx.spongysb.events.base.CancellableIslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * Called before a player teleports to an island.
 *
 * EVENT FIRED FROM:
 * - GridManager#homeTeleport
 */
public class IslandPreTeleportEvent extends CancellableIslandEvent {

    private final Player player;
    private final Reason type;

    private final Location location;

    public IslandPreTeleportEvent(Island island, Player player, Reason type, Location<World> location, Cause cause) {
        super(island, cause);

        this.player = player;
        this.type = type;
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public Reason getType() {
        return type;
    }

    public Location<World> getLocation() {
        return location;
    }


    public enum Reason {
        HOME,
        WARP,
        SPAWN

    }

}
