package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.gui.IslandUpgradesGUI;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandUpgradesCommand extends AbstractIslandCommand {

    @Subcommand("upgrade|upgrades")
    @Description("Change Island Upgrades")
    @CommandPermission("spongysb.island.upgrades")
    public void onPermissionsCommand(Player player) {

        SPlayer sPlayer = SPlayer.get(player);

        if (!sPlayer.isInIsland()) {
            msg(player, getMessages().player_not_in_island);
            return;
        }

        if (!sPlayer.hasPerm(IslandPerm.VIEW_UPGRADES, sPlayer.getIsland())) {
            msg(player, getMessages().no_permission);
            return;
        }

        IslandUpgradesGUI.open(player);

    }
}
