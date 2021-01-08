package games.synx.spongysb.objects;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.PlayerCache;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SPlayer {

  private UUID island_uuid;
  private final UUID player_uuid;
  private String island_role;
  private boolean island_bypass = false;

  private SPlayer(UUID island_uuid, UUID player_uuid, String island_role) {
    this.island_uuid = island_uuid;
    this.player_uuid = player_uuid;
    this.island_role = island_role;
  }

  /**
   * Get the DatabaseObject of a Player
   * @param player Sponge Player Object
   * @return SPlayer object
   */
  public static SPlayer get(Player player) {
    return get(player.getUniqueId());
  }

  /**
   * Get the DatabaseObject of a Player
   * @param uuid UUID of player
   * @return SPlayer object
   */
  public static SPlayer get(UUID uuid) {
    return PlayerCache.get(uuid);
  }

  /**
   * Fetch a SPlayer from the Database. NOT THREAD SAFE
   * @param uuid UUID of the player
   * @return SPlayer Object
   */
  public static SPlayer fetch(UUID uuid) {

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_PLAYER)) {

      preparedStatement.setString(1, uuid.toString());
      ResultSet rs = preparedStatement.executeQuery();

      if(rs.next()) {

        return new SPlayer(
                UUID.fromString(rs.getString("island_uuid")),
                uuid,
                rs.getString("island_role")
        );
      }

      return null;

    } catch (SQLException e) {
      SpongySB.get().getLogger().error("Could not fetch user from MySQL! (SPlayer#fetch). Is this intentional?");
      e.printStackTrace();
      return null;
    }

  }

  /**
   * Save a SPlayer Object to the database
   * @param sPlayer Player to be saved
   */
  public static void save(SPlayer sPlayer) {

    try (Connection connection = DatabaseManager.get().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(Statements.PLAYER_QUIT_UPDATE)) {
      preparedStatement.setString(1, sPlayer.getIslandUUID().toString());
      preparedStatement.setString(2, sPlayer.getIslandRole());
      preparedStatement.setString(3, sPlayer.getPlayerUUID().toString());

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Set the Players' Island
   * @param island Island Object
   */
  public void setIsland(Island island) {
    this.island_uuid = island.getIslandUUID();
  }

  /**
   * Set the Players' Island
   * @param uuid Island's UUID
   */
  public void setIsland(UUID uuid) {
    this.island_uuid = uuid;
  }

  /**
   * Set the Players' Island Permission Level
   * @param islandPermissionLevel Island Permission Level Enum Value
   */
  public void setIslandRole(IslandPermissionLevel islandPermissionLevel) {

    this.island_role = islandPermissionLevel.toString();

  }

  // TODO WRITE LOGIC FOR ISLAND PERMISSIONS // RANKS
  public boolean hasPerm(IslandPerm islandPerm, Location<World> world) {
    if(Island.getIslandAt(world) == null) {
      return false;
    }
    return getIsland().getIslandUUID().toString().equals(Island.getIslandAt(world).getIslandUUID().toString());
  }

  /**
   * Check if the Specified Player is in an Island
   * @return boolean
   */
  public boolean isInIsland() {
    return !island_uuid.equals(new UUID(0L, 0L));
  }

  /**
   * Check if Specified player has administrative bypass
   * @return boolean
   */
  public Boolean isBypassed() {
    return island_bypass;
  }

  /**
   * Set the Specified Players' Bypass Mode
   * @param bypassed boolean - Should they bypass island guard?
   */
  public void setBypassed(Boolean bypassed) {
    this.island_bypass = bypassed;

  }

  /**
   * Removes the player from their island on the database
   */
  public void removeFromIsland() {
    setIsland(new UUID(0L, 0L));
    setIslandRole(IslandPermissionLevel.NONE);
  }

  /**
   * @return The Players' Island
   */
  public Island getIsland() {
    return Island.get(island_uuid);
  }

  /**
   * @return The Players' Island UUID
   */
  public UUID getIslandUUID() {
    return this.island_uuid;
  }

  /**
   * @return The Players' UUID
   */
  public UUID getPlayerUUID() {
    return this.player_uuid;
  }

  /**
   * @return The Players' Island Role
   */
  public String getIslandRole() {
    return this.island_role;
  }

}
