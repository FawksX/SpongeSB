package games.synx.spongysb.events.base;

import games.synx.spongysb.objects.Island;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.UUID;

public abstract class IslandEvent extends AbstractEvent {

    private Island island;
    private Cause cause;

    public IslandEvent(Island island, Cause cause) {
        this.island = island;
        this.cause = cause;
    }

    @Override
    public Cause getCause() {
        return this.cause;
    }

    public Island getIsland() {
        return this.island;
    }

    public boolean isLocked() {
        return this.island.isLocked();
    }

    public int getProtectionRange() {
        return this.island.getProtectionRange();
    }

    public UUID getIslandOwnerUUID() {
        return this.island.getOwnerUUID();
    }

    public Location<World> getCenterLocation() {
        return this.island.getCenter();
    }

    public Location<World> getHomeLocation() {
        return this.island.getIslandHome();
    }

}
