package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandJoinEvent;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandJoinCommand extends AbstractIslandCommand {

  @Subcommand("join")
  @Description("Join another players' Island!")
  @Syntax("<island name>")
  @CommandPermission("spongysb.island.invite")
  public void onJoinCommand(Player player, String islandName) {

    SPlayer sPlayer = SPlayer.get(player);

    if(sPlayer.isInIsland()) {
      msg(player, getMessages().creation.must_leave_current_island);
      return;
    }

    Island island = Island.get(islandName);

    if(island == null) {
      formatMsg(player, getMessages().island_does_not_exist, islandName);
      return;
    }

    if(island.isInvited(player.getUniqueId())) {
      IslandJoinEvent islandJoinEvent = new IslandJoinEvent(player.getUniqueId(), island.getLeaderUUID(), island);
      sPlayer.setIsland(island);
      sPlayer.setIslandRole(IslandPermissionLevel.MEMBER);

      formatMsg(player, getMessages().invite.joined_island_successfully, island.getIslandName());
      island.revokeInvite(player.getUniqueId());
      Sponge.getEventManager().post(islandJoinEvent);

      island.broadcastToOnlineMembers(getMessages().invite.player_has_joined_island, player.getName());
      return;
    }

    formatMsg(player, getMessages().invite.could_not_join_island, island.getIslandName());

    }
}
