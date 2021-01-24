package games.synx.spongysb.commands;

import co.aikar.commands.apachecommonslang.ApacheCommonsLangUtil;
import com.google.common.collect.Lists;
import games.synx.pscore.command.PSCommandManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.commands.admin.*;
import games.synx.spongysb.commands.island.*;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

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
    getCommandManager().registerCommand(new IslandCoopCommand());
    getCommandManager().registerCommand(new IslandPromoteCommand());
    getCommandManager().registerCommand(new IslandDemoteCommand());
    getCommandManager().registerCommand(new IslandSethomeCommand());
    getCommandManager().registerCommand(new IslandLockCommand());
    getCommandManager().registerCommand(new IslandPermissionsCommand());
    getCommandManager().registerCommand(new IslandUpgradesCommand());
    getCommandManager().registerCommand(new IslandUnbanCommand());
    getCommandManager().registerCommand(new IslandBanCommand());
  }

  public void registerAdminCommands() {
    getLogger().info("Initialising Admin Commands");
    getCommandManager().registerCommand(new SpongeSBCommand());
    getCommandManager().registerCommand(new AdminBypassCommand());
    getCommandManager().registerCommand(new AdminDisbandCommand());
    getCommandManager().registerCommand(new AdminTeleportCommand());
    getCommandManager().registerCommand(new AdminRenameCommand());
  }

  @Override
  public void registerCompletions() {
    super.registerCompletions();

    getCommandManager().getCommandCompletions().registerCompletion("islands", p -> {

      List<String> islandNames = Lists.newArrayList();
      for(Island island : IslandCache.getAll().values()) {
        islandNames.add(island.getIslandName());
      }

      return islandNames;

    });

  }

}
