package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.gui.IslandCreateGUI;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.IslandNameUtil;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandCreateCommand extends AbstractIslandCommand {

  @Subcommand("new")
  @Description("Create a new Island")
  @Syntax("<island name>")
  @CommandPermission("spongysb.island.create")
  public void onCreateCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);

    if(sPlayer.isInIsland()) {
      msg(player, getMessages().is_in_island_error);
      return;
    }

    if(IslandNameUtil.isIslandNameTaken(name)) {
      formatMsg(player, getMessages().island_name_taken, name);
      return;
    }

    IslandCreateGUI.open(player, name);

  }


}
