package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.gui.CommandConfirmGUI;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.IslandUtil;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandRenameCommand extends AbstractIslandCommand {

  @Subcommand("rename")
  @Description("Rename your island")
  @Syntax("<island name>")
  @CommandPermission("spongysb.island.rename")
  public void onRenameCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);

    if (!sPlayer.isInIsland()) {
      msg(player, getMessages().player_not_in_island);
      return;
    }

    if(!sPlayer.hasPerm(IslandPerm.NAME, sPlayer.getIsland())) {
      msg(player, getMessages().no_permission);
      return;
    }

    if (IslandUtil.isIslandNameTaken(name)) {
      formatMsg(player, getMessages().creation.island_name_taken, name);
      return;
    }

    CommandConfirmGUI.open(player, (action) -> {
      Island island = sPlayer.getIsland();
      island.setIslandName(name);
      island.broadcastToOnlineMembers(getMessages().island_name_set_successfully, player.getName(), name);
      action.getPlayer().closeScreen();
    });




  }

}