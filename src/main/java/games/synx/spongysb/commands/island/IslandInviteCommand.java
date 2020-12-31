package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandInviteCommand extends AbstractIslandCommand {

  @Subcommand("invite")
  @Description("Invite a player to your Island!")
  @CommandPermission("spongysb.island.invite")
  public void onInviteCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);
    Island island = sPlayer.getIsland();

    if(!Sponge.getServer().getPlayer(name).isPresent()) {
      msg(player, getMessages().player_not_online);
      return;
    }

    Player targetInvite = Sponge.getServer().getPlayer(name).get();

    if(island.isInvited(targetInvite.getUniqueId().toString())) {
      island.revokeInvite(targetInvite.getUniqueId().toString());
      msg(player, String.format(getMessages().invite_revoked_successfully, targetInvite.getName()));
      return;
    }

    island.addInvite(targetInvite.getUniqueId().toString());
    msg(player, String.format(getMessages().invited_player_successfully, targetInvite.getName()));
    msg(targetInvite, String.format(getMessages().invited_to_island, player.getName(), island.getIslandName(), island.getIslandName()));

  }

}
