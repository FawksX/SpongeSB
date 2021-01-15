package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.pscore.util.PlayerUtil;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;

@CommandAlias("is|island")
public class IslandDemoteCommand extends AbstractIslandCommand {

    @Subcommand("demote")
    @Syntax("<player>")
    @CommandCompletion("@onlineplayers")
    @Description("Demote an Island Member in your island!")
    @CommandPermission("spongysb.island.demote")
    public void onPromoteCommand(Player player, String member) {

        SPlayer sPlayer = SPlayer.get(player);

        if(!sPlayer.hasPerm(IslandPerm.DEMOTE, sPlayer.getIsland())) {
            msg(player, getMessages().no_permission);
            return;
        }

        SPlayer sPlayerTarget;
        java.util.Optional<Player> playerTarget = Optional.empty();

        if(!Sponge.getServer().getPlayer(member).isPresent()) {
            sPlayerTarget = SPlayer.get(PlayerUtil.getOfflineSpongeUserUUID(member));

        } else {
            playerTarget = Sponge.getServer().getPlayer(member);
            sPlayerTarget = SPlayer.get(playerTarget.get());
        }

        if(sPlayerTarget.isInIsland(sPlayer.getIsland())) {
            msg(player, getMessages().demote.cannot_demote_non_island_member);
            return;
        }

        int promotion = sPlayerTarget.getIslandRole().getPosition() - 1;
        if(promotion < IslandPermissionLevel.MEMBER.getPosition()) {
            msg(player, getMessages().demote.cannot_demote_to_visitor);
            return;
        }

        IslandPermissionLevel oldLevel = sPlayerTarget.getIslandRole();
        sPlayerTarget.setIslandRole(IslandPermissionLevel.fromPosition(promotion));
        sPlayer.getIsland().broadcastToOnlineMembers(getMessages().demote.island_broadcast_promotion_success, player.getName(), member, oldLevel.toString(), IslandPermissionLevel.fromPosition(promotion).toString());


    }

}
