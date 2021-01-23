package games.synx.spongysb.commands.admin;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.commands.common.TeleportCommandCommon;
import games.synx.spongysb.events.IslandPreTeleportEvent;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("spongesb|ssb")
public class AdminTeleportCommand extends AbstractIslandCommand {

    @Subcommand("tp|teleport")
    @Description("Teleport to an Island")
    @Syntax("<island name>")
    @CommandPermission("spongysb.admin.tp")
    public void onAdminTeleportCommand(Player player, String name) {

        Island island = Island.get(name);

        if(island == null) {
            formatMsg(player, getMessages().island_does_not_exist, name);
            return;
        }

        TeleportCommandCommon.executeCommon(island, player, IslandPreTeleportEvent.Reason.ADMIN);

    }

}
