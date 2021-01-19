package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandSethomeCommand extends AbstractIslandCommand {

    @Subcommand("sethome")
    @Description("Set your new Island Home")
    @CommandPermission("spongysb.island.sethome")
    public void onSethomeCommand(Player player) {

        SPlayer sPlayer = SPlayer.get(player);

        if (!sPlayer.isInIsland()) {
            msg(player, getMessages().player_not_in_island);
            return;
        }

        if(!sPlayer.hasPerm(IslandPerm.SET_HOME, sPlayer.getIsland())) {
            msg(player, getMessages().no_permission);
            return;
        }

        if(sPlayer.atTheirIsland()) {
            if(player.getLocation().hasBlock()) {
                sPlayer.getIsland().setHome(player.getLocation());
                msg(player, getMessages().setHome.island_home_set_successfully);
                return;
            }

            msg(player, getMessages().setHome.unsafe_home_location);
            return;

        }

        msg(player, getMessages().setHome.can_only_set_home_in_your_island);

    }

}
