package games.synx.spongysb.commands.island;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.spongepowered.api.entity.living.player.Player;

public class IslandCommand extends BaseCommand {

  @Subcommand("help")
  @CatchUnknown @Default
  public void onDefault(Player player) {



  }

}
