package games.synx.spongysb.events;

import java.util.UUID;


import games.synx.spongysb.events.base.IslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.event.cause.Cause;


/**
 * Called whenever a player changes the ownership of an island.
 *
 * EVENT FIRED FROM:
 * - tbd..
 */
public class IslandChangeOwnerEvent extends IslandEvent {

    private final UUID oldOwner, newOwner;

    public IslandChangeOwnerEvent(Island island, Cause cause, UUID oldOwner, UUID newOwner) {
        super(island, cause);
        this.oldOwner = oldOwner;
        this.newOwner = newOwner;
    }

    public UUID getOldOwner() {
        return oldOwner;
    }

    public UUID getNewOwner() {
        return newOwner;
    }

}
