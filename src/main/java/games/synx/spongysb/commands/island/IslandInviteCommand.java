package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandInviteCommand extends AbstractIslandCommand {

  @Subcommand("invite")
  @Description("Invite a player to your Island!")
  @CommandCompletion("@spongeplayers")
  @Syntax("<player>")
  @CommandPermission("spongysb.island.invite")
  public void onInviteCommand(Player player, String name) {

    SPlayer sPlayer = SPlayer.get(player);
    Island island = sPlayer.getIsland();

    if(!Sponge.getServer().getPlayer(name).isPresent()) {
      msg(player, getMessages().player_not_online);
      return;
    }

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().invite.must_be_in_island_to_invite);
      return;
    }

    if(!sPlayer.hasPerm(IslandPerm.INVITE, island)) {
      msg(player, getMessages().no_permission);
      return;
    }

    Player targetInvite = Sponge.getServer().getPlayer(name).get();

    System.out.println("MemberCount" + island.getMemberCount() + "MemberLimit " + island.getMemberLimit() + island.isFull());
    if(island.isFull()) {
      formatMsg(player, getMessages().invite.island_is_full, targetInvite.getName());
      return;
    }

    if(island.isInvited(targetInvite.getUniqueId())) {
      island.revokeInvite(targetInvite.getUniqueId());
      formatMsg(player, getMessages().invite.invite_revoked_successfully, targetInvite.getName());
      island.broadcastToOnlineMembers(getMessages().invite.leader_revoked_invite, player.getName(), targetInvite.getName());
      return;
    }

    island.addInvite(targetInvite.getUniqueId());
    formatMsg(player, getMessages().invite.invited_player_successfully, targetInvite.getName());
    formatMsg(targetInvite, getMessages().invite.invited_to_island, player.getName(), island.getIslandName(), island.getIslandName());
    island.broadcastToOnlineMembers(getMessages().invite.leader_invited_player, player.getName(), targetInvite.getName());

  }

}
