package games.synx.spongysb.events.base;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.impl.AbstractEvent;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.UUID;

public abstract class IslandEvent extends AbstractEvent {

    private Island island;

    public IslandEvent(Island island) {
        this.island = island;
    }

    @Override
    public Cause getCause() {
        return Cause.builder().build(EventContext.builder().add(EventContextKeys.PLUGIN, SpongySB.get().getPluginContainer()).build());
    }

    public Island getIsland() {
        return this.island;
    }


}
