package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.gui.IslandPermissionsGUI;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandPermissionsCommand extends AbstractIslandCommand {

    @Subcommand("permissions")
    @Description("Change Island Permissions")
    @CommandPermission("spongysb.island.permissions")
    public void onPermissionsCommand(Player player) {

        SPlayer sPlayer = SPlayer.get(player);

        if (!sPlayer.isInIsland()) {
            msg(player, getMessages().player_not_in_island);
            return;
        }

        if (!sPlayer.hasPerm(IslandPerm.VIEW_PERMS, sPlayer.getIsland())) {
            msg(player, getMessages().no_permission);
            return;
        }

        IslandPermissionsGUI.open(player);

    }

}
