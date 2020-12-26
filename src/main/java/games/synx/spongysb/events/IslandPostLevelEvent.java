package games.synx.spongysb.events;

import games.synx.spongysb.events.base.CancellableIslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.event.cause.Cause;

/**
 * Called after an island value calculation has been completed.
 *
 * EVENT FIRED FROM:
 * - IslandValueCalculationTask#tidyUp
 */
public class IslandPostLevelEvent extends CancellableIslandEvent {


    private final long level;

    public IslandPostLevelEvent(Island island, long level, Cause cause) {
        super(island, cause);
        this.level = level;
    }

    public long getLevel() {
        return this.level;
    }

}
