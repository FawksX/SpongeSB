package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.pscore.util.PlayerUtil;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("is|island")
public class IslandWhoCommand extends AbstractIslandCommand {

    @Subcommand("who")
    @Description("Read Information about an island")
    @CommandCompletion("@islands")
    @CommandPermission("spongysb.island.who")
    public void onDefault(Player player, @Optional String name) {

        Island island = null;

        if (name == null) {
            if (SPlayer.get(player).isInIsland()) {
                island = SPlayer.get(player).getIsland();
            }
        } else {
            island = Island.get(name);
        }

        if (island == null) {
            formatMsg(player, getMessages().island_does_not_exist, name);
            return;
        }

        msg(player,
                "&8&m---------------&r &e&l" + island.getIslandName() + "&r &8&m---------------\n" +
                        "&6Leader: &f " + PlayerUtil.getOfflineSpongeUser(island.getLeaderUUID()).getName() + "\n" +
                        "&6Members: &f" + island.getMemberCount() + "&8/&f" + island.getMemberLimit() + "\n" +
                        "&6Island Size: &f " + island.getSize() + "\n" +
                        "&6Island Generator Level: &f " + island.getIslandGeneratorValue() + "\n" +
                        "&8&m-----------------------------------");

    }

}
