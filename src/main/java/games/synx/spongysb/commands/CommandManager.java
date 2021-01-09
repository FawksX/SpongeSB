package games.synx.spongysb.commands;

import games.synx.pscore.command.PSCommandManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.commands.admin.AdminBypassCommand;
import games.synx.spongysb.commands.admin.SpongeSBCommand;
import games.synx.spongysb.commands.island.*;

public class CommandManager extends PSCommandManager {

  public CommandManager() {
    super(SpongySB.get().getLogger(), SpongySB.get().getPluginContainer());
  }

  public void registerPlayerCommands() {
    getLogger().info("Initialising Player Commands");
    getCommandManager().enableUnstableAPI("help");
    getCommandManager().registerCommand(new IslandCommand());
    getCommandManager().registerCommand(new IslandCreateCommand());
    getCommandManager().registerCommand(new IslandGoCommand());
    getCommandManager().registerCommand(new IslandInviteCommand());
    getCommandManager().registerCommand(new IslandJoinCommand());
    getCommandManager().registerCommand(new IslandKickCommand());
    getCommandManager().registerCommand(new IslandLeaveCommand());
    getCommandManager().registerCommand(new IslandRenameCommand());
    getCommandManager().registerCommand(new IslandMakeleaderCommand());
    getCommandManager().registerCommand(new IslandDisbandCommand());

  }

  public void registerAdminCommands() {
    getLogger().info("Initialising Admin Commands");
    getCommandManager().registerCommand(new SpongeSBCommand());
    getCommandManager().registerCommand(new AdminBypassCommand());
  }

}
