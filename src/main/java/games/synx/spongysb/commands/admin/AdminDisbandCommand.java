package games.synx.spongysb.commands.admin;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.commands.common.DisbandCommandCommon;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("spongesb|ssb")
public class AdminDisbandCommand extends AbstractIslandCommand {

    @Subcommand("disband")
    @Description("Disband Players Island")
    @Syntax("<island name>")
    @CommandPermission("spongysb.admin.disband")
    public void onAdminBypassCommand(Player player, String name) {

        Island island = Island.get(name);

        if(island == null) {
            formatMsg(player, getMessages().island_does_not_exist, name);
            return;
        }

        DisbandCommandCommon.executeCommon(player, island);

        formatMsg(player, getMessages().admin.disband.admin_command_confirmation, name);

    }

}
