package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandLeaveEvent;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandLeaveCommand extends AbstractIslandCommand {

  @Subcommand("leave")
  @Description("Leave your island")
  @Syntax("")
  @CommandPermission("spongysb.island.leave")
  public void onLeaveCommand(Player player, @Optional String confirm) {

    if(!confirm.equals("confirm")) {
      msg(player, getMessages().leave.leave_confirm);
      return;
    }

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().leave.must_be_in_island_to_leave);
      return;
    }

    if(sPlayer.getIslandRole() == IslandPermissionLevel.LEADER) {
      msg(player, getMessages().must_disband_as_a_leader);
      return;
    }

    postEvent(new IslandLeaveEvent(player.getUniqueId(), sPlayer.getIsland().getLeaderUUID(), sPlayer.getIsland()));
    formatMsg(player, getMessages().leave.left_island, sPlayer.getIsland().getIslandName());
    sPlayer.removeFromIsland();
    player.setLocationSafely(WorldManager.get().getServerSpawn());
    sPlayer.getIsland().broadcastToOnlineMembers(getMessages().leave.player_left_island, player.getName());

  }

}