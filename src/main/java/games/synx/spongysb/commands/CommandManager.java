package games.synx.spongysb.commands;

import co.aikar.commands.SpongeCommandManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.commands.admin.AdminBypassCommand;
import games.synx.spongysb.commands.admin.SpongeSBCommand;
import games.synx.spongysb.commands.island.IslandCommand;
import games.synx.spongysb.commands.island.IslandCreateCommand;
import games.synx.spongysb.commands.island.IslandGoCommand;
import games.synx.spongysb.commands.island.IslandInviteCommand;
import games.synx.spongysb.commands.island.IslandJoinCommand;
import games.synx.spongysb.commands.island.IslandKickCommand;
import games.synx.spongysb.commands.island.IslandLeaveCommand;
import games.synx.spongysb.commands.island.IslandMakeleaderCommand;
import games.synx.spongysb.commands.island.IslandRenameCommand;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CommandManager {

  private SpongeCommandManager spongeCommandManager;

  private Logger logger = SpongySB.get().getLogger();

  public CommandManager() {

    logger.info("Initialising SpongeCommandManager");
    spongeCommandManager = new SpongeCommandManager(SpongySB.get().getPluginContainer());

    registerCompletions();
    registerPlayerCommands();
    registerAdminCommands();

  }

  public void registerPlayerCommands() {

    logger.info("Initialising Player Commands");
    spongeCommandManager.enableUnstableAPI("help");
    spongeCommandManager.registerCommand(new IslandCommand());
    spongeCommandManager.registerCommand(new IslandCreateCommand());
    spongeCommandManager.registerCommand(new IslandGoCommand());
    spongeCommandManager.registerCommand(new IslandInviteCommand());
    spongeCommandManager.registerCommand(new IslandJoinCommand());
    spongeCommandManager.registerCommand(new IslandKickCommand());
    spongeCommandManager.registerCommand(new IslandLeaveCommand());
    spongeCommandManager.registerCommand(new IslandRenameCommand());
    spongeCommandManager.registerCommand(new IslandMakeleaderCommand());

  }

  public void registerAdminCommands() {

    logger.info("Initialising Admin Commands");
    spongeCommandManager.registerCommand(new SpongeSBCommand());
    spongeCommandManager.registerCommand(new AdminBypassCommand());
  }

  public void registerCompletions() {

    spongeCommandManager.getCommandCompletions().registerCompletion("onlineplayers", p -> {
      List<String> names = Lists.newArrayList();
      for(Player player : Sponge.getServer().getOnlinePlayers()) {
        names.add(player.getName());
      }
      return Collections.unmodifiableList(names);
    });

  }

}
