package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.commands.common.DisbandCommandCommon;
import games.synx.spongysb.gui.CommandConfirmGUI;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandDisbandCommand extends AbstractIslandCommand {

  @Subcommand("disband")
  @Description("Leave your island")
  @CommandPermission("spongysb.island.disband")
  public void onDisbandCommand(Player player, @Optional String confirm) {

    if(confirm == null || !confirm.equals("confirm")) {
      msg(player, getMessages().disband.disband_confirm);
      return;
    }

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().disband.disband_must_be_in_island);
      return;
    }

    if(sPlayer.getIslandRole() != IslandPermissionLevel.LEADER) {
      msg(player, getMessages().disband.only_leader_can_disband);
      return;
    }

    Island island = sPlayer.getIsland();

    CommandConfirmGUI.open(player, (action) -> {
      DisbandCommandCommon.executeCommon(player, island);
      action.getPlayer().closeScreen();
    });



  }
}