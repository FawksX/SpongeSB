package games.synx.spongysb.commands;

import co.aikar.commands.SpongeCommandManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.commands.island.IslandCommand;
import games.synx.spongysb.commands.island.IslandCreateCommand;
import games.synx.spongysb.commands.island.IslandGoCommand;
import org.slf4j.Logger;

public class CommandManager {

  private SpongeCommandManager spongeCommandManager;

  private Logger logger = SpongySB.get().getLogger();

  public CommandManager() {

    logger.info("Initialising SpongeCommandManager");
    spongeCommandManager = new SpongeCommandManager(SpongySB.get().getPluginContainer());

    registerPlayerCommands();

  }

  public void registerPlayerCommands() {

    logger.info("Initialising Player Commands");
    spongeCommandManager.enableUnstableAPI("help");
    spongeCommandManager.registerCommand(new IslandCommand());
    spongeCommandManager.registerCommand(new IslandCreateCommand());
    spongeCommandManager.registerCommand(new IslandGoCommand());

  }

}
