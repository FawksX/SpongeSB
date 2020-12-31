package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.IslandNameUtil;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandRenameCommand extends AbstractIslandCommand {

  @Subcommand("rename")
  @Description("Rename your island")
  @CommandPermission("spongysb.island.rename")
  public void onRenameCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);

    if (!sPlayer.isInIsland()) {
      msg(player, getMessages().player_not_in_island);
      return;
    }

    if(!sPlayer.getIslandRole().equals(IslandPermissionLevel.LEADER.toString())) {
      msg(player, getMessages().player_is_not_leader);
      return;
    }

    if (IslandNameUtil.isIslandNameTaken(name)) {
      msg(player, String.format(getMessages().island_name_taken, name));
      return;
    }

    Island island = sPlayer.getIsland();
    island.setIslandName(name);
    island.broadcastToOnlineMembers(getMessages().island_name_set_successfully, player.getName(), name);



  }

}