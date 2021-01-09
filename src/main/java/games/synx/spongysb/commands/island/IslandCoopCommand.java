package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandCoopCommand extends AbstractIslandCommand {

    @Subcommand("coop")
    @Description("Coop a player on your island!")
    @Syntax("<player>")
    @CommandPermission("spongysb.island.coop")
    public void onCoopCommand(Player player, String name) {

        SPlayer sPlayer = SPlayer.get(player);
        Island island = sPlayer.getIsland();

        if(!Sponge.getServer().getPlayer(name).isPresent()) {
            msg(player, getMessages().player_not_online);
            return;
        }

        if(!sPlayer.isInIsland()) {
            msg(player, getMessages().must_be_in_island_to_invite);
        }

        Player targetCoop = Sponge.getServer().getPlayer(name).get();

        if(island.isCoop(targetCoop)) {
            island.revokeCoop(targetCoop);
            formatMsg(player, getMessages().coop.revoked_successfully, targetCoop.getName());
            island.broadcastToOnlineMembers(getMessages().coop.revoked_broadcast, player.getName(), targetCoop.getName());
            return;
        }

        island.addCoop(targetCoop);
        formatMsg(player, getMessages().coop.added_successfully, targetCoop.getName());
        formatMsg(targetCoop, getMessages().coop.added_message, player.getName(), island.getIslandName(), island.getIslandName());
        island.broadcastToOnlineMembers(getMessages().coop.added_broadcast, player.getName(), targetCoop.getName());

    }

}