package games.synx.spongysb.objects;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class Island {

  private UUID island_uuid;
  private UUID leader_uuid;
  private String island_name;
  private Location<World> location;
  private int member_amount;
  private String banned_members;
  private Location<World> homeLocation;

  private Island(UUID island_uuid, UUID leader_uuid, String island_name,
                 String center_location, int member_amount, String banned_members, String home_location) {

    this.island_uuid = island_uuid;
    this.leader_uuid = leader_uuid;
    this.island_name = island_name;
    this.member_amount = member_amount;

    // TODO should be changed to a List<String>
    this.banned_members = banned_members;


    //TODO SIMPLIFY

    // STORED IN FORMAT: x,z
    double[] arr = Stream.of(center_location.split(",")).mapToDouble(Double::parseDouble).toArray();
    this.location = new Location<World>(WorldManager.get().getWorld(), arr[0], ConfigManager.get().getConf().world.island_paste_height, arr[1]);

    // HOME LOCATION
    double[] homeLoc = Stream.of(home_location.split(",")).mapToDouble(Double::parseDouble).toArray();
    this.homeLocation = new Location<World>(WorldManager.get().getWorld(), homeLoc[0], homeLoc[1], homeLoc[2]);
  }

  public static Island get(UUID island_uuid) {

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ISLAND);
      preparedStatement.setString(1, island_uuid.toString());
      ResultSet rs = preparedStatement.executeQuery();
      rs.next();

      return new Island(
          island_uuid,
          UUID.fromString(rs.getString("leader_uuid")),
          rs.getString("island_name"),
          rs.getString("center_location"),
          rs.getInt("member_amount"),
          rs.getString("banned_members"),
          rs.getString("home_location")
      );

    } catch (SQLException e) {
      SpongySB.get().getLogger().error("Could not fetch island, did something go wrong?");
      e.printStackTrace();
      return null;
    }

  }

  /**
   * Adds the Island to the spongysb_islands database
   * @param islandUUID The Islands' UUID
   * @param leaderUUID The Island Leaders UUID
   * @param islandName The name of the Island
   * @param centerLoc Center Location serialised as (x,z)
   * @return Island Object
   */
  public static Island addIsland(UUID islandUUID, UUID leaderUUID, String islandName, Location<World> centerLoc) {

    String centerSerialised = centerLoc.getBlockX() + "," + centerLoc.getBlockZ();
    String homeLocSerialised = centerLoc.getBlockX() + "," + centerLoc.getBlockY() + "," + centerLoc.getBlockZ();

    try(Connection connection = SpongySB.get().getDatabaseManager().getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.INSERT_ISLAND);
      preparedStatement.setString(1, islandUUID.toString());
      preparedStatement.setString(2, leaderUUID.toString());
      preparedStatement.setString(3, islandName);
      preparedStatement.setString(4, centerSerialised);
      preparedStatement.setInt(5, 1);
      preparedStatement.setString(6, "");

      // BY DEFAULT CENTER LOCATION IS THE SAME AS THE PASTE LOCATION, AS IT WILL BE A SOLID LOCATION IF SCHEM IS MADE CORRECTLY.
      preparedStatement.setString(7, homeLocSerialised);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return new Island(islandUUID, leaderUUID, islandName, centerSerialised, 1, "", homeLocSerialised);

  }

  public UUID getIslandUUID() {
    return this.island_uuid;
  }

  public UUID getLeaderUUID() {
    return this.leader_uuid;
  }

  public String getIslandName() {
    return this.island_name;
  }

  public Location<World> getCenterLocation() {
    return this.location;
  }

  public int getMemberCount() {
    return this.member_amount;
  }

  public SPlayer getLeader() {
    return SPlayer.get(leader_uuid);
  }

  public Location<World> getHomeLocation() {
    return this.homeLocation;
  }


}
