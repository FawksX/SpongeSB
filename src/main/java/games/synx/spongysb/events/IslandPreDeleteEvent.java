package games.synx.spongysb.events;

import games.synx.spongysb.events.base.IslandEvent;
import games.synx.spongysb.objects.Island;

import java.util.UUID;

/**
 * Called before an island is deleted, event fires before {@link IslandDeleteEvent}.
 *
 * EVENT FIRED FROM:
 * - Island#deleteIsland
 */
public class IslandPreDeleteEvent extends IslandEvent {

    private final UUID playerUUID;

    public IslandPreDeleteEvent(UUID playerUUID, Island island) {
        super(island);

        this.playerUUID = playerUUID;
    }

    /**
     * @return the player's UUID
     */
    public UUID getPlayerUUID() {
        return playerUUID;
    }

}