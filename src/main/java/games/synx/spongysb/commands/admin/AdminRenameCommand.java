package games.synx.spongysb.commands.admin;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.util.IslandUtil;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("spongesb|ssb")
public class AdminRenameCommand extends AbstractIslandCommand {

    @Subcommand("rename")
    @Description("Rename an Island")
    @Syntax("<island name> <new name>")
    @CommandPermission("spongysb.admin.rename")
    public void onAdminRenameCommand(Player player, String name, String newname) {

        Island island = Island.get(name);

        if(island == null) {
            formatMsg(player, getMessages().island_does_not_exist, name);
            return;
        }

        if (IslandUtil.isIslandNameTaken(newname)) {
            formatMsg(player, getMessages().creation.island_name_taken, name);
            return;
        }

        island.setIslandName(newname);
        island.broadcastToOnlineMembers(getMessages().island_name_set_successfully, player.getName(), newname);
        formatMsg(player, getMessages().admin.rename.admin_command_notification, name, newname);

    }

}
