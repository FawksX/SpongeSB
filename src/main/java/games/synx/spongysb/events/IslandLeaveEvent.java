package games.synx.spongysb.events;

import java.util.UUID;

import games.synx.spongysb.events.base.CancellableIslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.event.cause.Cause;

/**
 * Called whenever a player leaves an Island.
 *
 * EVENT FIRED FROM:
 * - APlayer#leaveIsland
 */
public class IslandLeaveEvent extends CancellableIslandEvent {

    private final UUID player, oldTeamLeader;

    public IslandLeaveEvent(UUID player, UUID oldTeamLeader, Island island, Cause cause) {
        super(island, cause);

        this.player = player;
        this.oldTeamLeader = oldTeamLeader;
    }

    public UUID getPlayer() {
        return player;
    }

    public UUID getOldTeamLeader() {
        return oldTeamLeader;
    }

}
