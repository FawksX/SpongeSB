package games.synx.spongysb.listeners.islandguard;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class VanillaIslandGuard extends AbstractIslandGuard {

  public VanillaIslandGuard() {
    SpongySB.get().getLogger().info("Initialising PlayerGuard");
  }

  /**
   * Stops the player from breaking outside of their island
   * @param event ChangeBlockEvent.Break (SpongeAPI)
   */
  @Listener
  public void onBlockBreak(ChangeBlockEvent.Break event, @Root Player player) {
    if(passesGenericIslandChecks(player, IslandPerm.BREAK)) return;
    event.setCancelled(true);
  }

  /**
   * Stops the player from placing outside of their island
   * @param event ChangeBlockEvent.Place (SpongeAPI)
   * @param player The player in question of the event
   */
  @Listener
  public void onBlockPlace(ChangeBlockEvent.Place event, @Root Player player) {
    if(passesGenericIslandChecks(player, IslandPerm.PLACE)) return;
    event.setCancelled(true);
  }

  /**
   * Stops the player from leaving their island through walking
   * @param event MoveEntityEvent.Position
   * @param player The player in question of the event
   */
  @Listener
  public void onPlayerMove(MoveEntityEvent.Position event, @Root Player player) {
    SPlayer sPlayer = SPlayer.get(player);

    if(isBypassed(sPlayer)) return;
    if(player.getHealthData().health().get() == 0) return;
    if(isNotInWorld(player)) return;
    if(event.getFromTransform().getLocation().equals(event.getToTransform().getLocation())) return;

    Location<World> to = event.getToTransform().getLocation();
    Location<World> from = event.getFromTransform().getLocation();
    if(Island.getIslandAt(to) == null) {
      event.setCancelled(true);
      return;
    }

    if(sPlayer.getIsland().getIslandUUID().toString().equals(Island.getIslandAt(to).getIslandUUID().toString())) return;

      event.setCancelled(true);

  }

  /**
   * Stops the player from teleporting to another users' island if they do not have permission
   * @param event MoveEntityEvent.Teleport
   * @param player The player in question of the event
   */
  @Listener
  public void onPlayerTeleport(MoveEntityEvent.Teleport event, @Root Player player) {
    SPlayer sPlayer = SPlayer.get(player);

    if(isBypassed(sPlayer)) return;
    if(isNotInWorld(player)) return;
    if(event.getFromTransform().getLocation().equals(event.getToTransform().getLocation())) return;

    Location<World> to = event.getToTransform().getLocation();
    if(isNotInWorld(to)) return;

    if(Island.getIslandAt(to) == null || !sPlayer.isInIsland()) {
      event.setCancelled(true);
      player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(ConfigManager.get().getMessages().only_allowed_to_teleport_to_own_island));
      return;
    }


    if(sPlayer.getIsland().getIslandUUID().toString().equals(Island.getIslandAt(to).getIslandUUID().toString())) return;

    if(Island.getIslandAt(to).getIslandUUID() != sPlayer.getIsland().getIslandUUID()) {
      event.setCancelled(true);
      player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(ConfigManager.get().getMessages().only_allowed_to_teleport_to_own_island));
    }


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

  private boolean isBypassed(SPlayer player) {
    return player.isBypassed();
  }

}
