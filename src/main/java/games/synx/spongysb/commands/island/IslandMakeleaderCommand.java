package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandMakeleaderCommand extends AbstractIslandCommand {

  @Subcommand("makeleader")
  @Description("Give your island to someone else")
  @CommandPermission("spongysb.island.makeleader")
  public void onJoinCommand(Player player, String newLeader) {

    SPlayer sPlayer = SPlayer.get(player);
    Island island = sPlayer.getIsland();

    if(!Sponge.getServer().getPlayer(newLeader).isPresent()) {
      msg(player, getMessages().player_not_online);
      return;
    }

    Player targetLeader = Sponge.getServer().getPlayer(newLeader).get();
    SPlayer sTargetLeader = SPlayer.get(targetLeader);

    if(!sPlayer.getIslandRole().equals(IslandPermissionLevel.LEADER.toString())) {
      msg(player, getMessages().player_is_not_leader);
      return;
    }

    if(!sTargetLeader.getIslandUUID().toString().equals(sPlayer.getIslandUUID().toString())) {
      msg(player, String.format(getMessages().must_be_in_island_to_give_leadership, targetLeader.getName()));
      return;
    }

    sPlayer.setIslandRole(IslandPermissionLevel.MEMBER);
    sTargetLeader.setIslandRole(IslandPermissionLevel.LEADER);
    island.setLeaderUUID(targetLeader.getUniqueId().toString());

    island.broadcastToOnlineMembers(getMessages().leader_changed_broadcast, player.getName(), targetLeader.getName());



  }
}