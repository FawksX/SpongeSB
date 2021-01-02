package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandJoinEvent;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

@CommandAlias("is|island")
public class IslandJoinCommand extends AbstractIslandCommand {

  @Subcommand("join")
  @Description("Join another players' Island!")
  @CommandPermission("spongysb.island.invite")
  public void onJoinCommand(Player player, String islandName) {

    SPlayer sPlayer = SPlayer.get(player);

    if(sPlayer.isInIsland()) {
      msg(player, getMessages().must_leave_current_island);
      return;
    }

    Island island = Island.get(islandName);

    if(island == null) {
      msg(player, String.format(getMessages().island_does_not_exist, islandName));
      return;
    }

    if(island.isInvited(player.getUniqueId().toString())) {
      IslandJoinEvent islandJoinEvent = new IslandJoinEvent(player.getUniqueId(), island.getLeaderUUID(), island);
      sPlayer.setIsland(island);
      sPlayer.setIslandRole(IslandPermissionLevel.MEMBER);

      msg(player, String.format(getMessages().joined_island_successfully, island.getIslandName()));
      island.revokeInvite(player.getUniqueId().toString());
      Sponge.getEventManager().post(islandJoinEvent);

      island.broadcastToOnlineMembers(getMessages().player_has_joined_island, player.getName());
      return;
    }

    msg(player, String.format(getMessages().could_not_join_island, island.getIslandName()));

    }
}
