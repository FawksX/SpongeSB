package games.synx.spongysb.events;

import java.util.UUID;

import games.synx.spongysb.events.base.CancellableIslandEvent;
import games.synx.spongysb.objects.Island;

/**
 * Called whenever a player joins an Island.
 *
 * EVENT FIRED FROM:
 * - APlayer#joinIsland
 */
public class IslandJoinEvent extends CancellableIslandEvent {

    private final UUID player, newTeamLeader;

    private boolean alt = false;

    public IslandJoinEvent(UUID player, UUID newTeamLeader, Island island) {
        super(island);

        this.player = player;
        this.newTeamLeader = newTeamLeader;
    }

    public IslandJoinEvent(UUID player, UUID newTeamLeader, Island island, boolean alt) {
        this(player, newTeamLeader, island);

        this.alt = alt;
    }

    public boolean isAlt() {
        return this.alt;
    }

    public UUID getPlayer() {
        return player;
    }

    public UUID getNewTeamLeader() {
        return newTeamLeader;
    }

}
