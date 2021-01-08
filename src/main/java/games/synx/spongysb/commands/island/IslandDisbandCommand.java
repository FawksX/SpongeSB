package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandDeleteEvent;
import games.synx.spongysb.events.IslandPreDeleteEvent;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

@CommandAlias("is|island")
public class IslandDisbandCommand extends AbstractIslandCommand {

  @Subcommand("disband")
  @Description("Leave your island")
  @CommandPermission("spongysb.island.disband")
  public void onDisbandCommand(Player player, @Optional String confirm) {

    if(confirm.isEmpty() || !confirm.equals("confirm")) {
      msg(player, getMessages().disband_confirm);
      return;
    }

    getLogger().info("got to point 1!");

    SPlayer sPlayer = SPlayer.get(player);

    getLogger().info("got to point 2!");

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().disband_must_be_in_island);
      return;
    }

    if(!sPlayer.getIslandRole().equals(IslandPermissionLevel.LEADER.toString())) {
      msg(player, getMessages().only_leader_can_disband);
      return;
    }

    Island island = sPlayer.getIsland();

    IslandPreDeleteEvent preDeleteEvent = new IslandPreDeleteEvent(player.getUniqueId(), island);
    Sponge.getEventManager().post(preDeleteEvent);

    for(UUID pp : island.getIslandMembers()) {
      SPlayer islandMember = SPlayer.get(pp);
      islandMember.removeFromIsland();
      if(Sponge.getServer().getPlayer(pp).isPresent()) {
        formatMsg(Sponge.getServer().getPlayer(pp).get(), getMessages().island_disbanded, player.getName());
        player.setLocationSafely(WorldManager.get().getServerSpawn());
      }
    }

    IslandDeleteEvent islandDeleteEvent = new IslandDeleteEvent(player, island.getCenterLocation(), island);
    Sponge.getEventManager().post(islandDeleteEvent);


  }
}