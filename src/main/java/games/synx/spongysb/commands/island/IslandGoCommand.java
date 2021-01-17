package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandEnterEvent;
import games.synx.spongysb.events.IslandPreTeleportEvent;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

@CommandAlias("is|island")
public class IslandGoCommand extends AbstractIslandCommand {

  @Subcommand("go")
  @Description("Go to your Island")
  @CommandPermission("spongysb.island.go")
  public void onGoCommand(Player player) {

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().player_not_in_island);
      return;
    }

    if(!sPlayer.hasPerm(IslandPerm.HOME, sPlayer.getIsland())) {
      msg(player, getMessages().no_permission);
      return;
    }

    postEvent(new IslandPreTeleportEvent(sPlayer.getIsland(), player, IslandPreTeleportEvent.Reason.HOME, sPlayer.getIsland().getHomeLocation()));

    Location<World> teleportLocation = sPlayer.getIsland().getHomeLocation();

    if(player.setLocationSafely(teleportLocation)) {
      IslandEnterEvent event = new IslandEnterEvent(sPlayer.getPlayerUUID(), sPlayer.getIsland(), teleportLocation);
      player.setLocationSafely(teleportLocation);
      Sponge.getEventManager().post(event);
      msg(player, getMessages().teleport.teleporting_to_your_island);

    } else {
      if(player.setLocationSafely(sPlayer.getIsland().getCenterLocation())) {
        IslandEnterEvent event = new IslandEnterEvent(sPlayer.getPlayerUUID(), sPlayer.getIsland(), teleportLocation);
        player.setLocationSafely(sPlayer.getIsland().getCenterLocation());
        Sponge.getEventManager().post(event);
        msg(player, getMessages().teleport.teleporting_to_default_home_location);
      } else {
        msg(player, getMessages().teleport.could_not_teleport_to_island);
      }
    }
  }
}
