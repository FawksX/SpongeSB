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

@CommandAlias("is|island")
public class IslandUnbanCommand extends AbstractIslandCommand {

    @Subcommand("unban")
    @Description("Unban a player on your island!")
    @Syntax("<player>")
    @CommandPermission("spongysb.island.unban")
    public void onUnbanCommand(Player player, String name) {

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

        if(!Sponge.getServer().getPlayer(name).isPresent()) {
            sPlayerTarget = SPlayer.get(PlayerUtil.getOfflineSpongeUserUUID(name));

        } else {
            sPlayerTarget = SPlayer.get(Sponge.getServer().getPlayer(name).get());
        }

        if(sPlayerTarget.isBanned(island.getIslandUUID())) {
            BanCache.remove(sPlayerTarget.getPlayerUUID(), island.getIslandUUID());
            BanCache.delete(sPlayerTarget.getPlayerUUID(), island.getIslandUUID());
            formatMsg(player, getMessages().ban.unban_success, name);
            island.broadcastToOnlineMembers(getMessages().ban.player_unbanned_broadcast, player.getName(), name);
            return;
        }

        formatMsg(player, getMessages().ban.player_not_banned, name);

    }
}
