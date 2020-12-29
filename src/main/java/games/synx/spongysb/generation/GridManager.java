package games.synx.spongysb.generation;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.events.IslandNewEvent;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.storage.Statements;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.util.UUIDUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.stream.Stream;

public class GridManager {

  private static GridManager instance = new GridManager();

  public static GridManager get() {
    return instance;
  }

  /**
   * Creates a new Island, making the Player specified the Owner.
   * This does not do any checks to see if they are already in an island, this should be done by the /is new Command.
   * @param player the player making the island
   * @param schematic Schematic Handler
   * @param islandName name of the island the player has specified
   */
  public void newIsland(Player player, SchematicHandler schematic, String islandName) {

    UUID islandUUID = UUIDUtil.generateUniqueIslandUUID();
    UUID leaderUUID = player.getUniqueId();

    SPlayer sPlayer = SPlayer.get(player);

    Location<World> islandLoc = getNextIslandLocation();

    schematic.pasteSchematicAsync(islandLoc, player);

    Island island = Island.addIsland(islandUUID, leaderUUID, islandName, islandLoc);

    sPlayer.setIsland(island);
    sPlayer.setIslandRole(IslandPermissionLevel.LEADER);

    IslandNewEvent islandNewEvent = new IslandNewEvent(player, island, islandLoc);
    Sponge.getEventManager().post(islandNewEvent);


  }


  private Location<World> getNextIslandLocation() {
    Location<World> last = getLastLocation();
    return getNextGridLocation(last);
  }

  private Location<World> getLastLocation() {

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection()) {

      Location<World> last;

      ResultSet rs = connection.prepareStatement(Statements.GET_LAST_LOCATION).executeQuery();

      // THERE IS NO LAST ISLAND LOCATION!
      if(!rs.next()) {
        last = new Location<World>(
            WorldManager.get().getWorld(),
            0,
            ConfigManager.get().getConf().world.island_paste_height,
            0);
      } else {
        String rawLoc = rs.getString("lastisland");
        double[] arr = Stream.of(rawLoc.split(",")).mapToDouble(Double::parseDouble).toArray();
        last = new Location<World>(WorldManager.get().getWorld(), arr[0], ConfigManager.get().getConf().world.island_paste_height, arr[1]);
      }

      return last;

    } catch (SQLException e) {
      e.printStackTrace();
      SpongySB.get().getLogger().error("COULD NOT ACCESS DATABASE");
      return null;
    }

  }

  private Location<World> getNextGridLocation(Location<World> lastCenterLocation) {

    int x = lastCenterLocation.getBlockX();
    int z = lastCenterLocation.getBlockZ();

    int nextX = lastCenterLocation.getBlockX();
    int nextZ = lastCenterLocation.getBlockZ();

    if (x < z) {
      if (-1 * x < z) {
        nextX = (x + ConfigManager.get().getConf().world.islandDistance);
      } else {
        nextZ = (z + ConfigManager.get().getConf().world.islandDistance);
      }
    } else if (x > z) {
      if (-1 * x >= z) {
        nextX = (x - ConfigManager.get().getConf().world.islandDistance);
      } else {
        nextZ = (z - ConfigManager.get().getConf().world.islandDistance);
      }
    } else if (x <= 0) {
      nextZ = (z + ConfigManager.get().getConf().world.islandDistance);
    } else {
      nextZ = (z - ConfigManager.get().getConf().world.islandDistance);
    }

    Location<World> next = new Location<World>(WorldManager.get().getWorld(), nextX, ConfigManager.get().getConf().world.island_paste_height, nextZ);

    saveLastLocation(next);

    return next;

  }

  private void saveLastLocation(Location<World> location) {
    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.UPDATE_LAST_GRID_ISLAND);
      preparedStatement.setString(1, location.getBlockX() + "," + location.getBlockZ());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private boolean inWorld(Location<World> location) {
    return location.getExtent() == WorldManager.get().getWorld();
  }


}