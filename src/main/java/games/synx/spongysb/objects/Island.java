package games.synx.spongysb.objects;

import com.google.common.collect.Lists;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.serializer.TextSerializers;
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

  private final UUID island_uuid;
  private UUID leader_uuid;
  private String island_name;
  private final Location<World> location;
  private Location<World> homeLocation;
  private List<String> invited_members = Lists.newArrayList();


  private Island(UUID island_uuid, UUID leader_uuid, String island_name,
                 String center_location, String home_location) {

    this.island_uuid = island_uuid;
    this.leader_uuid = leader_uuid;
    this.island_name = island_name;


    //TODO SIMPLIFY

    // STORED IN FORMAT: x,z
    double[] arr = Stream.of(center_location.split(",")).mapToDouble(Double::parseDouble).toArray();
    this.location = new Location<World>(WorldManager.get().getWorld(), arr[0], ConfigManager.get().getConf().world.island_paste_height, arr[1]);

    // HOME LOCATION
    double[] homeLoc = Stream.of(home_location.split(",")).mapToDouble(Double::parseDouble).toArray();
    this.homeLocation = new Location<World>(WorldManager.get().getWorld(), homeLoc[0], homeLoc[1], homeLoc[2]);
  }

  /**
   * Returns an Island from the IslandCache
   * @param islandUUID Island UUID
   * @return Island
   */
  public static Island get(UUID islandUUID) {
    return IslandCache.get(islandUUID);
  }

  public static Island get(String islandName) {

    for(Island island : IslandCache.getAll().values()) {
      if(island.getIslandName().equals(islandName)) {
        return island;
      }
    }
    return null;
  }

  /**
   * Saves the island to the database
   * @param island Island to be saved
   */
  public static void save(Island island) {

    String centerSerialised = island.getCenterLocation().getBlockX() + "," + island.getCenterLocation().getBlockZ();
    String homeLocSerialised = island.getHomeLocation().getBlockX() + "," + island.getHomeLocation().getBlockY() + "," + island.getHomeLocation().getBlockZ();

    try (Connection connection = DatabaseManager.get().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.SAVE_ALL_ISLANDS)) {
      preparedStatement.setString(1, island.getLeaderUUID().toString());
      preparedStatement.setString(2, island.getIslandName());
      preparedStatement.setString(3, centerSerialised);
      preparedStatement.setString(4, homeLocSerialised);
      preparedStatement.setString(5, island.getIslandUUID().toString());

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fetch a List of all Islands in the Database.
   * This is then used in IslandCache#setup to put every island into a hashmap based on it's ID.
   * @return List of Islands.
   */
  public static List<Island> getAll() {

    List<Island> islands = Lists.newArrayList();

    try (Connection connection = DatabaseManager.get().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ALL_ISLANDS)) {

      ResultSet rs = preparedStatement.executeQuery();

      while(rs.next()) {

        Island newIsland = new Island(
                UUID.fromString(rs.getString("island_uuid")),
                UUID.fromString(rs.getString("leader_uuid")),
                rs.getString("island_name"),
                rs.getString("center_location"),
                rs.getString("home_location"));

        islands.add(newIsland);

      }

      return islands;

    } catch (SQLException e) {
      e.printStackTrace();
      return islands;
    }
  }

  /**
   * Gets an Island from it's UUID
   * @param island_uuid the UUID of the island stored in the database
   * @return Island
   */
  public static Island fetch(UUID island_uuid) {

    try (Connection connection = DatabaseManager.get().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ISLAND)) {

      preparedStatement.setString(1, island_uuid.toString());
      ResultSet rs = preparedStatement.executeQuery();
      rs.next();

      return new Island(
          island_uuid,
          UUID.fromString(rs.getString("leader_uuid")),
          rs.getString("island_name"),
          rs.getString("center_location"),
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

    try(Connection connection = DatabaseManager.get().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Statements.INSERT_ISLAND)) {

      preparedStatement.setString(1, islandUUID.toString());
      preparedStatement.setString(2, leaderUUID.toString());
      preparedStatement.setString(3, islandName);
      preparedStatement.setString(4, centerSerialised);

      // BY DEFAULT CENTER LOCATION IS THE SAME AS THE PASTE LOCATION, AS IT WILL BE A SOLID LOCATION IF SCHEM IS MADE CORRECTLY.
      preparedStatement.setString(5, homeLocSerialised);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    Island newIsland = new Island(islandUUID, leaderUUID, islandName, centerSerialised, homeLocSerialised);
    IslandCache.add(newIsland);

    return newIsland;

  }

  /**
   * Get an Island at a Sponge Location instance
   * @param location - location at which you want to find the island
   * @return getIslandAt(x,z)
   */
  public static Island getIslandAt(Location<World> location) {
    return getIslandAt(location.getBlockX(), location.getBlockZ());
  }

  /**
   * Get an Island from it's coordinates
   * This takes the two integers and does some mathsy stuff with them to get the nearest island center.
   * @param x X coordinate of the location
   * @param z Z coordinate of the location
   * @return Island
   */
  public static Island getIslandAt(int x, int z) {
    int nearestX =
        (int) ((Math.round((double) x / ConfigManager.get().getConf().world.islandDistance) * ConfigManager.get().getConf().world.islandDistance));
    int nearestZ =
        (int) ((Math.round((double) z / ConfigManager.get().getConf().world.islandDistance) * ConfigManager.get().getConf().world.islandDistance));

      for(Island island : IslandCache.getAll().values()) {
        if(island.getCenterLocation().getBlockX() == nearestX && island.getCenterLocation().getBlockZ() == nearestZ) {
          return island;
        }
      }

    return null;

  }

  /**
   * Removes a UUID from pending invites
   * @param uuid UUID of the user to remove from the database
   */
  public void addInvite(UUID uuid) {
    invited_members.add(uuid.toString());
  }

  public void revokeInvite(UUID uuid) {
    invited_members.remove(uuid.toString());
  }

  /**
   * Checks if a player is invited to an island
   * @param uuid the player who has been invited
   * @return if they have been invited
   */
  public boolean isInvited(UUID uuid) {
    return invited_members.contains(uuid.toString());
  }

  /**
   * Checks if the UUID of a player is the leader of the island
   * @param uuid UUID of the user (in String form)
   * @return boolean (isLeader)
   */
  public boolean isLeader(String uuid) {
    return getLeaderUUID().toString().equals(uuid);
  }

  /**
   * Get a list of all members in the island.
   * @return list of members in the island
   */
  public List<UUID> getIslandMembers() {

    try (Connection connection = DatabaseManager.get().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_PLAYERS_IN_ISLAND)) {
      preparedStatement.setString(1, getIslandUUID().toString());
      ResultSet rs = preparedStatement.executeQuery();

      List<UUID> island_members = Lists.newArrayList();

      while(rs.next()) {
        island_members.add(UUID.fromString(rs.getString("player_uuid")));
      }

      return island_members;

    } catch (SQLException e) {
      e.printStackTrace();
      return Lists.newArrayList();
    }
  }

  /**
   * Broadcasts messages to all members of the Island who are currently online
   * @param message the Message to send
   * @param replacements String replacements used in String.format()
   */
  public void broadcastToOnlineMembers(String message, Object ... replacements) {
    for(UUID member : getIslandMembers()) {
      if(Sponge.getServer().getPlayer(member).isPresent()) {
        Sponge.getServer().getPlayer(member).get().sendMessage(TextSerializers.FORMATTING_CODE.deserialize(String.format(message, replacements)));
      }
    }
  }

  /**
   * Sets a new Island Name
   * @param newName name to set the islands to
   */
  public void setIslandName(String newName) {
    this.island_name = newName;
  }

  public void setLeaderUUID(UUID newLeader) {
    this.leader_uuid = newLeader;
  }

  /**
   * Get an Islands UUID
   * @return Island UUID
   */
  public UUID getIslandUUID() {
    return this.island_uuid;
  }

  /**
   * Get an Islands' Leaders' UUID
   * @return Leader UUID
   */
  public UUID getLeaderUUID() {
    return this.leader_uuid;
  }

  /**
   * Get an Islands' Name
   * @return Island Name
   */
  public String getIslandName() {
    return this.island_name;
  }

  /**
   * Get an Islands' Center Location
   * This is useful for a number of things as it acts as a static reference point
   * @return Islands' Center Location
   */
  public Location<World> getCenterLocation() {
    return this.location;
  }

  /**
   * Gets an Islands' total Member Count
   * @return member count
   */
  public int getMemberCount() {
    return getIslandMembers().size();
  }

  /**
   * Returns an SPlayer object of the Leader
   * @return Leader as SPlayer
   */
  public SPlayer getLeader() {
    return SPlayer.get(getLeaderUUID());
  }

  /**
   * Gets the Home location set by the Island as a safe teleport space
   * @return Location of Home
   */
  public Location<World> getHomeLocation() {
    return this.homeLocation;
  }


}
