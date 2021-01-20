package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.*;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.events.IslandDeleteEvent;
import games.synx.spongysb.events.IslandPreDeleteEvent;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.util.PlayerUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

@CommandAlias("is|island")
public class IslandDisbandCommand extends AbstractIslandCommand {

  @Subcommand("disband")
  @Description("Leave your island")
  @CommandPermission("spongysb.island.disband")
  public void onDisbandCommand(Player player, @Optional String confirm) {

    if(confirm == null || !confirm.equals("confirm")) {
      msg(player, getMessages().disband.disband_confirm);
      return;
    }

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().disband.disband_must_be_in_island);
      return;
    }

    if(sPlayer.getIslandRole() != IslandPermissionLevel.LEADER) {
      msg(player, getMessages().disband.only_leader_can_disband);
      return;
    }

    Island island = sPlayer.getIsland();

    IslandPreDeleteEvent preDeleteEvent = new IslandPreDeleteEvent(player.getUniqueId(), island);
    Sponge.getEventManager().post(preDeleteEvent);

    for(UUID pp : island.getIslandMembers()) {
      SPlayer islandMember = SPlayer.get(pp);
      islandMember.removeFromIsland();
      if(Sponge.getServer().getPlayer(pp).isPresent()) {
        formatMsg(Sponge.getServer().getPlayer(pp).get(), getMessages().disband.island_disbanded, player.getName());
      }
    }

    island.setActive(false);
    IslandDeleteEvent islandDeleteEvent = new IslandDeleteEvent(player, island.getCenterLocation(), island);
    Sponge.getEventManager().post(islandDeleteEvent);

    for(Player aPlayer : PlayerUtil.getAllPlayersAtIsland(island)) {
      PlayerUtil.teleportToSpawn(aPlayer);
    }


  }
}