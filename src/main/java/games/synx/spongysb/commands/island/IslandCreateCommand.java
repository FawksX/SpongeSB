package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.generation.SchematicHandler;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.IslandNameUtil;
import org.spongepowered.api.entity.living.player.Player;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@CommandAlias("is|island")
public class IslandCreateCommand extends AbstractIslandCommand {

  @Subcommand("new")
  @Description("Create a new Island")
  @CommandPermission("spongysb.island.create")
  public void onCreateCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);

    if(sPlayer.isInIsland()) {
      msg(player, getMessages().is_in_island_error);
      return;
    }

    if(IslandNameUtil.isIslandNameTaken(name)) {
      msg(player, String.format(getMessages().island_name_taken, name));
      return;
    }

    Path schematic = Paths.get(SpongySB.get().schematicsDir.toString() + File.separator + "default.schematic");
    GridManager.get().newIsland(player, new SchematicHandler(schematic.toFile()), "test");

    msg(player, getMessages().island_created_successfully);

  }


}
