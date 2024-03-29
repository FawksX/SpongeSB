package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.pscore.util.PlayerUtil;
import games.synx.spongysb.cache.BanCache;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.objects.enums.IslandPerm;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;

@CommandAlias("is|island")
public class IslandBanCommand extends AbstractIslandCommand {

    @Subcommand("ban")
    @Description("Ban a player on your island!")
    @CommandCompletion("@spongeplayers")
    @Syntax("<player>")
    @CommandPermission("spongysb.island.ban")
    public void onBanCommand(Player player, String name) {

        SPlayer sPlayer = SPlayer.get(player);
        Island island = sPlayer.getIsland();

        if(!sPlayer.isInIsland()) {
            msg(player, getMessages().ban.must_be_in_island);
            return;
        }

        if(!sPlayer.hasPerm(IslandPerm.BAN, sPlayer.getIsland())) {
            msg(player, getMessages().no_permission);
            return;
        }

        SPlayer sPlayerTarget;
        java.util.Optional<Player> playerTarget = Optional.empty();

        if(!Sponge.getServer().getPlayer(name).isPresent()) {
            sPlayerTarget = SPlayer.get(PlayerUtil.getOfflineSpongeUserUUID(name));

        } else {
            sPlayerTarget = SPlayer.get(Sponge.getServer().getPlayer(name).get());
        }

        if(sPlayer.getIsland() == sPlayerTarget.getIsland()) {
            msg(player, getMessages().ban.cannot_ban_member);
            return;
        }

        if(!sPlayerTarget.isBanned(island.getIslandUUID())) {
            BanCache.add(sPlayerTarget.getPlayerUUID(), island.getIslandUUID());
            formatMsg(player, getMessages().ban.ban_success, name);
            island.broadcastToOnlineMembers(getMessages().ban.success_broadcast, player.getName(), name);
            return;
        }

        formatMsg(player, getMessages().ban.player_is_banned, name);

    }
}
