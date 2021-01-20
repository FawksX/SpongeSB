package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import games.synx.pscore.util.PlayerUtil;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;

@CommandAlias("is|island")
public class IslandKickCommand extends AbstractIslandCommand {

  @Subcommand("kick")
  @Description("Kick a player from your island")
  @Syntax("<player>")
  @CommandPermission("spongysb.island.kick")
  public void onKickCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.hasPerm(IslandPerm.INVITE, sPlayer.getIsland())) {
      msg(player, getMessages().no_permission);
      return;
    }

    SPlayer sPlayerTarget;
    Optional<Player> playerTarget = Optional.empty();

    if(!Sponge.getServer().getPlayer(name).isPresent()) {
      sPlayerTarget = SPlayer.get(PlayerUtil.getOfflineSpongeUserUUID(name));

    } else {
      sPlayerTarget = SPlayer.get(Sponge.getServer().getPlayer(name).get());
    }

    if(!sPlayer.getIslandUUID().toString().equals(sPlayerTarget.getIslandUUID().toString())) {
      formatMsg(player, getMessages().player_is_not_in_island, name);
      return;
    }

    sPlayerTarget.removeFromIsland();
    formatMsg(player, getMessages().kick.player_has_been_removed_from_island, name);
    sPlayer.getIsland().broadcastToOnlineMembers(getMessages().kick.leader_kicked_player, player.getName(), name);

    if(playerTarget.isPresent()) {
      playerTarget.get().setLocationSafely(WorldManager.get().getServerSpawn());
      formatMsg(playerTarget.get(), getMessages().kick.you_have_been_removed_from_your_island, sPlayer.getIsland().getIslandName(), player.getName());
    }

  }

}