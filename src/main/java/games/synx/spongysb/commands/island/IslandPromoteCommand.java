package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.PlayerUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;

@CommandAlias("is|island")
public class IslandPromoteCommand extends AbstractIslandCommand {

    @Subcommand("promote")
    @Syntax("<player>")
    @CommandCompletion("@onlineplayers")
    @Description("Promote an Island Member in your island!")
    @CommandPermission("spongysb.island.promote")
    public void onPromoteCommand(Player player, String member) {

        SPlayer sPlayer = SPlayer.get(player);

        if(!sPlayer.hasPerm(IslandPerm.PROMOTE, sPlayer.getIsland())) {
            msg(player, getMessages().no_permission);
            return;
        }

        SPlayer sPlayerTarget;
        java.util.Optional<Player> playerTarget = Optional.empty();

        if(!Sponge.getServer().getPlayer(member).isPresent()) {
            sPlayerTarget = SPlayer.get(PlayerUtil.getOfflineUserUUID(member));

        } else {
            playerTarget = Sponge.getServer().getPlayer(member);
            sPlayerTarget = SPlayer.get(playerTarget.get());
        }

        if(sPlayerTarget.getIsland() != sPlayer.getIsland()) {
            msg(player, getMessages().promote.cannot_promote_non_island_member);
            return;
        }

        int promotion = sPlayerTarget.getIslandRole().getPosition() + 1;
        if(promotion > IslandPermissionLevel.ADMIN.getPosition()) {
            msg(player, getMessages().promote.cannot_promote_to_leader);
            return;
        }

        sPlayerTarget.setIslandRole(IslandPermissionLevel.fromPosition(promotion));
        sPlayer.getIsland().broadcastToOnlineMembers(getMessages().promote.island_broadcast_promotion_success, player.getName(), member, IslandPermissionLevel.fromPosition(promotion).toString());


    }

}
