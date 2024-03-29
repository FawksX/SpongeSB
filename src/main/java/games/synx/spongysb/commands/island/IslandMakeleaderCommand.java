package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandChangeOwnerEvent;
import games.synx.spongysb.gui.CommandConfirmGUI;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandMakeleaderCommand extends AbstractIslandCommand {

  @Subcommand("makeleader")
  @Syntax("<player>")
  @CommandCompletion("@spongeplayers")
  @Description("Give your island to someone else")
  @CommandPermission("spongysb.island.makeleader")
  public void onMakeLeaderCommand(Player player, String newLeader) {

    SPlayer sPlayer = SPlayer.get(player);
    Island island = sPlayer.getIsland();

    if(!Sponge.getServer().getPlayer(newLeader).isPresent()) {
      msg(player, getMessages().player_not_online);
      return;
    }

    Player targetLeader = Sponge.getServer().getPlayer(newLeader).get();
    SPlayer sTargetLeader = SPlayer.get(targetLeader);

    if(sPlayer.getIslandRole() != IslandPermissionLevel.LEADER) {
      msg(player, getMessages().player_is_not_leader);
      return;
    }

    if(!sTargetLeader.getIslandUUID().toString().equals(sPlayer.getIslandUUID().toString())) {
      formatMsg(player, getMessages().must_be_in_island_to_give_leadership, targetLeader.getName());
      return;
    }

    CommandConfirmGUI.open(player, (action) -> {
      sPlayer.setIslandRole(IslandPermissionLevel.MEMBER);
      sTargetLeader.setIslandRole(IslandPermissionLevel.LEADER);
      island.setLeaderUUID(targetLeader.getUniqueId());

      SPlayer.save(sPlayer);
      SPlayer.save(sTargetLeader);

      island.broadcastToOnlineMembers(getMessages().leader_changed_broadcast, player.getName(), targetLeader.getName());

      postEvent(new IslandChangeOwnerEvent(island, sPlayer.getPlayerUUID(), sTargetLeader.getPlayerUUID()));
      action.getPlayer().closeScreen();
    });

  }
}