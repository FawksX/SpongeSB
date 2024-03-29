package games.synx.spongysb.generation;

import games.synx.pscore.util.AsyncUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.events.IslandNewEvent;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.util.IslandUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
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

  private static final GridManager INSTANCE = new GridManager();

  public static GridManager get() {
    return INSTANCE;
  }

  /**
   * Creates a new Island, making the Player specified the Owner.
   * This does not do any checks to see if they are already in an island, this should be done by the /is new Command.
   * @param player the player making the island
   * @param schematic Schematic Handler
   * @param islandName name of the island the player has specified
   */
  public void newIsland(Player player, SchematicHandler schematic, String islandName) {

    UUID islandUUID = IslandUtil.generateUniqueIslandUUID();
    UUID leaderUUID = player.getUniqueId();

    SPlayer sPlayer = SPlayer.get(player);

    Location<World> islandLoc = getNextIslandLocation();

    schematic.pasteSchematicAsync(islandLoc, player);

    AsyncUtil.async(() -> {

      Island island = Island.addIsland(islandUUID, leaderUUID, islandName, islandLoc);

      sPlayer.setIsland(island);
      sPlayer.setIslandRole(IslandPermissionLevel.LEADER);


      IslandNewEvent islandNewEvent = new IslandNewEvent(player, island, islandLoc);
      Sponge.getEventManager().post(islandNewEvent);

      SPlayer.save(sPlayer);

    });

  }

  /**
   * Gets the next center location for a new island
   * @return Sponge Location
   */
  private Location<World> getNextIslandLocation() {
    return getNextGridLocation(getLastLocation());
  }

  /**
   * Fetches the Last Island Center
   * @return Last Center Location
   */
  private Location<World> getLastLocation() {
    try (Connection connection = DatabaseManager.get().getConnection();
         ResultSet rs = connection.prepareStatement(Statements.GET_LAST_LOCATION).executeQuery()) {

      Location<World> last;

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

  /**
   * Fetches the next Grid location
   * @param lastCenterLocation Last Island to be created's center location
   * @return the next Grid Location
   */
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

  /**
   * Overwrite the Last Location stored in the database with a new one
   * @param location Location to overwrite with
   */
  private void saveLastLocation(Location<World> location) {
    try (Connection connection = DatabaseManager.get().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.UPDATE_LAST_GRID_ISLAND)) {

      preparedStatement.setString(1, location.getBlockX() + "," + location.getBlockZ());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Returns if an Island is on the grid
   * @param loc Location to check
   * @return If it is on the grid or not
   */
  public boolean onGrid(Location<World> loc) {
    int x = loc.getBlockX();
    int z = loc.getBlockZ();

    return onGrid(x, z);
  }

  /**
   * Returns if two coordinates are on the grid
   * @param x X Coordinate
   * @param z Z Coordinate
   * @return If it is on the grid or not
   */
  public boolean onGrid(int x, int z) {
    if ((x) % ConfigManager.get().getConf().world.islandDistance != 0) return false;
    return (z % ConfigManager.get().getConf().world.islandDistance == 0);
  }

  /**
   * Checks if an Entity is in the Island World
   * @param entity Entity in question
   * @return If it is in the Island World
   */
  public boolean inWorld(Entity entity) {
    return inWorld(entity.getLocation());
  }

  /**
   * Checks if a Location is in the Island World
   * @param location Location in question
   * @return If it is in the Island World
   */
  public boolean inWorld(Location<World> location) {
    return location.getExtent() == WorldManager.get().getWorld();
  }


}
