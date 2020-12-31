package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.Optional;

@CommandAlias("is|island")
public class IslandKickCommand extends AbstractIslandCommand {

  @Subcommand("kick")
  @Description("Kick a player from your island")
  @CommandPermission("spongysb.island.kick")
  public void onKickCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);

    if(!SPlayer.get(player).getIsland().isLeader(player.getUniqueId().toString())) {
      msg(player, getMessages().player_is_not_leader);
      return;
    }


    SPlayer sPlayerTarget;
    Optional<Player> playerTarget = Optional.empty();

    if(!Sponge.getServer().getPlayer(name).isPresent()) {
      Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
      User offlinePlayer = userStorage.get().get(name).get();
      sPlayerTarget = SPlayer.get(offlinePlayer.getUniqueId());

    } else {
      playerTarget = Sponge.getServer().getPlayer(name);

      sPlayerTarget = SPlayer.get(playerTarget.get());
    }

    System.out.println(sPlayerTarget.getIslandUUID().toString());

    if(!sPlayer.getIslandUUID().toString().equals(sPlayerTarget.getIslandUUID().toString())) {
      msg(player, String.format(getMessages().player_is_not_in_island, name));
      return;
    }

    sPlayerTarget.removeFromIsland();
    msg(player, String.format(getMessages().player_has_been_removed_from_island, name));
    sPlayer.getIsland().broadcastToOnlineMembers(getMessages().leader_kicked_player, player.getName(), name);

    if(playerTarget.isPresent()) {
      playerTarget.get().setLocationSafely(WorldManager.get().getServerSpawn());
      msg(playerTarget.get(), String.format(getMessages().you_have_been_removed_from_your_island, sPlayer.getIsland().getIslandName(), player.getName()));
    }

  }

}