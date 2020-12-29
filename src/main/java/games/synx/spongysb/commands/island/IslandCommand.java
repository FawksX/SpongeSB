package games.synx.spongysb.commands.island;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandCommand extends AbstractIslandCommand {

  @Subcommand("help")
  @CatchUnknown @Default @HelpCommand
  public void onDefault(Player player, CommandHelp help) {

    msg(player, getMessages().helpCommand.header);
    help.showHelp();

  }

}
