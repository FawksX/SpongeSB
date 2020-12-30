package games.synx.spongysb.commands.admin;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("spongesb|ssb")
public class SpongeSBCommand extends AbstractIslandCommand {

  @Subcommand("help")
  @CatchUnknown @Default
  @HelpCommand
  public void onAdminDefault(Player player, CommandHelp help) {

    help.showHelp();

  }

}
