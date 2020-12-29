package games.synx.spongysb.events;

import java.util.UUID;

import games.synx.spongysb.events.base.IslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;


/**
 * Called when a player enter's an island's protected area.
 *
 * EVENT FIRED FROM:
 * - IslandGuardEngine#handleMovementEvents
 * - PlayerGuardEngine#onJoin
 * - PlayerGuardEngine#onTeleport
 */
public class IslandEnterEvent extends IslandEvent {

    private final Location<World> location;
    private final UUID uuid;

    public IslandEnterEvent(UUID player, Island island, Location<World> location) {
        super(island);

        this.uuid = player;
        this.location = location;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Location<World> getLocation() {
        return this.location;
    }

}