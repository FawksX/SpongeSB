package games.synx.spongysb.events;

import games.synx.spongysb.events.base.IslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

/**
 * Called whenever a player creates a new island.
 *
 * EVENT FIRED FROM:
 * - GridEngine#newIsland
 */
public class IslandNewEvent extends IslandEvent {

    private final SchematicHandler schematic;
    private final Player player;

    public IslandNewEvent(Player player, SchematicHandler schematic, Island island, Cause cause) {
        super(island, cause);

        this.player = player;
        this.schematic = schematic;
    }

    public Player getPlayer() {
        return this.player;
    }

    public SchematicHandler getSchematic() {
        return this.schematic;
    }

}
