package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandLeaveEvent;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandLeaveCommand extends AbstractIslandCommand {

  @Subcommand("leave")
  @Description("Leave your island")
  @CommandPermission("spongysb.island.leave")
  public void onLeaveCommand(Player player, String confirm) {

    if(!confirm.equals("confirm")) {
      msg(player, getMessages().leave_confirm);
      return;
    }

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().must_be_in_island_to_invite);
      return;
    }

    if(sPlayer.getIslandRole().equals(IslandPermissionLevel.LEADER.toString())) {
      msg(player, getMessages().must_disband_as_a_leader);
      return;
    }

    msg(player, String.format(getMessages().left_island, sPlayer.getIsland().getIslandName()));
    sPlayer.removeFromIsland();
    player.setLocationSafely(WorldManager.get().getServerSpawn());
    sPlayer.getIsland().broadcastToOnlineMembers(getMessages().player_left_island, player.getName());

  }

}