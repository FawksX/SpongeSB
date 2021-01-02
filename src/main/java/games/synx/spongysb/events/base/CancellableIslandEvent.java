package games.synx.spongysb.events.base;

import games.synx.spongysb.objects.Island;
import org.spongepowered.api.event.Cancellable;

public abstract class CancellableIslandEvent extends IslandEvent implements Cancellable {

    private boolean cancelled = false;

    public CancellableIslandEvent(Island island) {
        super(island);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
