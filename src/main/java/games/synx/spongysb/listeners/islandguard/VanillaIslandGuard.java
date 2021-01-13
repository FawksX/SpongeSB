package games.synx.spongysb.listeners.islandguard;

import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.IslandUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketWorldBorder;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldBorder;

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

    Location<World> to = event.getToTransform().getLocation();
    if(isNotInWorld(to)) return;
    if(isNotInWorld(to)) return;

    if(Island.getIslandAt(to) == null) {
      event.setCancelled(true);
      MessageUtil.msg(player, ConfigManager.get().getMessages().not_allowed_to_teleport_here);
      return;
    }

    if(sPlayer.hasPerm(IslandPerm.ENTRY, to)) return;

    event.setCancelled(true);
    MessageUtil.msg(player, ConfigManager.get().getMessages().not_allowed_to_teleport_here);

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



}
