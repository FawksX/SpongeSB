package games.synx.spongysb.events;

import games.synx.spongysb.events.base.IslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * Called whenever an island is deleted, after {@link IslandPreDeleteEvent}
 *
 * EVENT FIRED FROM:
 * - Island#deleteIsland
 */
public class IslandDeleteEvent extends IslandEvent {

	private final Location<World> location;
	private final Player player;

	public IslandDeleteEvent(Player player, Location<World> oldLocation, Island island) {
		super(island);

		this.player = player;
		this.location = oldLocation;
	}

	public Player getPlayer() {
		return player;
	}

	public Location<World> getLocation() {
		return location;
	}

}
