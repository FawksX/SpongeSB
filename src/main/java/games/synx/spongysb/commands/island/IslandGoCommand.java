package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.commands.common.TeleportCommandCommon;
import games.synx.spongysb.events.IslandPreTeleportEvent;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandGoCommand extends AbstractIslandCommand {

  @Subcommand("go")
  @Description("Go to your Island")
  @CommandPermission("spongysb.island.go")
  public void onGoCommand(Player player) {

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().player_not_in_island);
      return;
    }

    if(!sPlayer.hasPerm(IslandPerm.HOME, sPlayer.getIsland())) {
      msg(player, getMessages().no_permission);
      return;
    }

    TeleportCommandCommon.executeCommon(SPlayer.get(player).getIsland(), player, IslandPreTeleportEvent.Reason.HOME);
  }
}
