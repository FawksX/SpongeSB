package games.synx.spongysb.events.base;

import games.synx.pscore.util.CauseUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

public abstract class IslandEvent extends AbstractEvent {

    private Island island;

    public IslandEvent(Island island) {
        this.island = island;
    }

    @Override
    public Cause getCause() {
        return CauseUtil.getCause(SpongySB.get().getPluginContainer());
    }

    public Island getIsland() {
        return this.island;
    }


}
