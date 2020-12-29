package games.synx.spongysb.events;

import games.synx.spongysb.events.base.IslandEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * Called whenever a player creates a new island.
 *
 * EVENT FIRED FROM:
 * - GridEngine#newIsland
 */
public class IslandNewEvent extends IslandEvent {

  private final Player player;
  private final Island island;
  private final Location<World> islandLocation;

  public IslandNewEvent(Player player, Island island, Location<World> islandLocation) {
    super(island);

    this.player = player;
    this.island = island;
    this.islandLocation = islandLocation;
  }

  public Player getPlayer() {
    return this.player;
  }

  public Island getIsland() {
    return this.island;
  }

  public Location<World> getIslandLocation() {
    return this.islandLocation;
  }

}