package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandLockCommand extends AbstractIslandCommand {

    @Subcommand("lock")
    @Description("Lock/Unlock your island from all visitors!")
    @CommandPermission("spongysb.island.lock")
    public void onCoopCommand(Player player) {

        SPlayer sPlayer = SPlayer.get(player);
        Island island = sPlayer.getIsland();

        if (!sPlayer.isInIsland()) {
            msg(player, getMessages().lock.must_be_in_island);
            return;
        }

        if (!sPlayer.hasPerm(IslandPerm.IS_LOCK, sPlayer.getIsland())) {
            msg(player, getMessages().no_permission);
            return;
        }


        if (island.getIslandPermissions().get(IslandPerm.ENTRY) == IslandPermissionLevel.NONE) {
            island.getIslandPermissions().replace(IslandPerm.ENTRY, IslandPermissionLevel.MEMBER);
            island.broadcastToOnlineMembers(getMessages().lock.island_is_now_locked_broadcast, player.getName());
            return;
        }

        island.getIslandPermissions().replace(IslandPerm.ENTRY, IslandPermissionLevel.NONE);
        island.broadcastToOnlineMembers(getMessages().lock.island_is_now_unlocked_broadcast, player.getName());

    }

}