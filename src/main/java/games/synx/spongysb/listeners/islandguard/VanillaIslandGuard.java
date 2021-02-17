package games.synx.spongysb.listeners.islandguard;

import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.IslandUtil;
import io.github.nucleuspowered.nucleus.api.events.NucleusTeleportEvent;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.item.ItemTypes;
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

    Island island = Island.getIslandAt(to);

    if(island == null) {
      event.setCancelled(true);
      return;
    }

    if(sPlayer.isBanned(island)) {
      event.setCancelled(true);
      MessageUtil.msg(player, getConfigManager().getMessages().ban.teleport_ban_notification, island.getIslandName());
      return;
    }

    if(sPlayer.hasPerm(IslandPerm.ENTRY, player.getLocation())) return;

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

    event.setCancelled(onTeleportAbstract(player, event.getToTransform().getLocation()));

  }

  /**
   * Stops a Nucleus Teleport Event from occuring if they do not have permission
   * @param event NucleusTeleportEvent.AboutToTeleport
   * @param player Player in question of the event
   */
  @Listener(order = Order.FIRST)
  public void onNucleusTeleport(NucleusTeleportEvent.AboutToTeleport event, @Root Player player) {
    SPlayer sPlayer = SPlayer.get(player);

    if(isBypassed(sPlayer)) return;
    if(isNotInWorld(player)) return;

    event.setCancelled(onTeleportAbstract(player, event.getToTransform().getLocation()));
  }

  /**
   * Changes the Border upon player teleport
   * @param event MoveEntityEvent.Teleport
   * @param player Player which has teleported
   */
  @Listener(order = Order.LAST)
  public void onBorderChange(MoveEntityEvent.Teleport event, @Root Player player) {
    IslandUtil.changeBorder(player, event.getToTransform().getLocation());
  }

  /**
   * Prevents a user dropping an item if they do not have Island Permission
   * @param event DropItemEvent [All Instances of Dropping]
   * @param player Player which has attempted to drop
   */
  @Listener
  public void onInventoryDrop(DropItemEvent event, @Root Player player) {
    if(passesGenericIslandChecks(player, IslandPerm.ITEM_DROP)) return;
    event.setCancelled(true);
  }

  /**
   * Prevents a player from picking up an item if they do not have Island Permission
   * @param event ChangeInventoryEvent.Pickup.Pre
   * @param player Player which has attempted to pickup
   */
  @Listener
  public void onInventoryPickup(ChangeInventoryEvent.Pickup.Pre event, @Root Player player) {
    if(passesGenericIslandChecks(player, IslandPerm.ITEM_PICKUP)) return;
    event.setCancelled(true);
  }

  /**
   * Common Logic used between Teleport Event Listeners
   * @param player Player in Question
   * @param to The teleport destination
   * @return If the event is cancelled or not
   */
  public boolean onTeleportAbstract(Player player, Location<World> to) {

    if(isNotInWorld(to)) return false;

    if(Island.getIslandAt(to) == null) {
      MessageUtil.msg(player, ConfigManager.get().getMessages().teleport.not_allowed_to_teleport_here);
      return true;
    }

    if(SPlayer.get(player).isBanned(Island.getIslandAt(to))) {
      MessageUtil.msg(player, getConfigManager().getMessages().ban.teleport_ban_notification, Island.getIslandAt(to).getIslandName());
      return true;
    }

    if(SPlayer.get(player).hasPerm(IslandPerm.ENTRY, to)) return false;

    MessageUtil.msg(player, ConfigManager.get().getMessages().teleport.not_allowed_to_teleport_here);
    return true;

  }

  @Listener
  public void onVoidDying(MoveEntityEvent.Position event, @Root Player player) {

    if(isNotInWorld(player)) return;
    SPlayer sPlayer = SPlayer.get(player);

    if(player.getLocation().getBlockY() < 0) {

      if(sPlayer.isInIsland(Island.getIslandAt(player.getLocation()))) {
        player.setLocationSafely(sPlayer.getIsland().getHomeLocation());

      } else {
        player.setLocationSafely(WorldManager.get().getServerSpawn());
        MessageUtil.msg(player, getConfigManager().getMessages().teleport.void_to_spawn);
      }
    }

  }

}
