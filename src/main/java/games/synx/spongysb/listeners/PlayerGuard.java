package games.synx.spongysb.listeners;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class PlayerGuard {

  public PlayerGuard() {
    SpongySB.get().getLogger().info("Initialising PlayerGuard");
  }

  /**
   * Stops the player from breaking outside of their island
   * @param event ChangeBlockEvent.Break (SpongeAPI)
   */
  @Listener
  public void onBlockBreak(ChangeBlockEvent.Break event) {
    Player player = (Player) event.getSource();
    SPlayer sPlayer = SPlayer.get(player);

    if(isNotInWorld(player)) return;
    if(sPlayer.hasPerm(sPlayer, IslandPerm.BREAK, player.getLocation())) return;

    event.setCancelled(true);
  }

  /**
   * Stops the player from placing outside of their island
   * @param event ChangeBlockEvent.Place (SpongeAPI)
   */
  @Listener
  public void onBlockPlace(ChangeBlockEvent.Place event, @Root Player player) {
    SPlayer sPlayer = SPlayer.get(player);

    if(isNotInWorld(player)) return;
    if(sPlayer.hasPerm(sPlayer, IslandPerm.PLACE, player.getLocation())) return;

    event.setCancelled(true);
  }


  // --------------------------------------------------------- //
  // REPETITIVE ISLANDGUARD METHODS
  // --------------------------------------------------------- //

  private boolean isNotInWorld(Entity entity) {
    return !GridManager.get().inWorld(entity.getLocation());
  }

  private boolean isNotInWorld(Location<World> location) {
    return !GridManager.get().inWorld(location);
  }

}
