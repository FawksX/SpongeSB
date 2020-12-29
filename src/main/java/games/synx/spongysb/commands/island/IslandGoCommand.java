package games.synx.spongysb.commands.island;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@CommandAlias("is|island")
public class IslandGoCommand extends AbstractIslandCommand {

  @Subcommand("go")
  @Description("Go to your Island")
  @CommandPermission("spongysb.island.go")
  public void onGoCommand(Player player) {

    SPlayer sPlayer = SPlayer.get(player);

    if(!sPlayer.isInIsland()) {
      msg(player, getMessages().player_not_in_island);
      return;
    }

    Location<World> teleportLocation = sPlayer.getIsland().getHomeLocation();
    System.out.println(teleportLocation);


    if(player.setLocationSafely(teleportLocation)) {
      player.setLocationSafely(teleportLocation);
      msg(player, getMessages().teleporting_to_your_island);
    } else {
      if(player.setLocationSafely(sPlayer.getIsland().getCenterLocation())) {
        player.setLocationSafely(sPlayer.getIsland().getCenterLocation());
        msg(player, getMessages().teleporting_to_default_home_location);
      } else {
        msg(player, getMessages().could_not_teleport_to_island);
      }
    }

  }


}