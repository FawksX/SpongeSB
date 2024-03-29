package games.synx.spongysb.objects;

import games.synx.pscore.util.AsyncUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.BanCache;
import games.synx.spongysb.cache.PlayerCache;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SPlayer {

  private final UUID PLAYER_UUID;

  private UUID island_uuid;
  private String island_role;
  private boolean island_bypass = false;

  private SPlayer(UUID island_uuid, UUID player_uuid, String island_role) {
    this.island_uuid = island_uuid;
    this.PLAYER_UUID = player_uuid;
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
   * Fetch a SPlayer from the Database.
   * @param uuid UUID of the player
   * @return SPlayer Object
   */
  public static SPlayer fetch(UUID uuid) {

    try (Connection connection = DatabaseManager.get().getConnection();
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
      SpongySB.get().getLogger().error("Could not fetch user from MySQL!");
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
    PreparedStatement preparedStatement = connection.prepareStatement(Statements.INSERT_PLAYER)) {

      preparedStatement.setString(1, sPlayer.getPlayerUUID().toString());
      preparedStatement.setString(2, sPlayer.getIslandUUID().toString());
      preparedStatement.setString(3, sPlayer.getIslandRole().toString());


      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a new SPlayer object based on the player
   * @param player Player to make the object for
   * @return SPlayer
   */
  public static SPlayer newSPlayer(Player player) {
    return new SPlayer(
            new UUID(0L, 0L),
            player.getUniqueId(),
            IslandPermissionLevel.NONE.toString()
    );
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

  /**
   * Checks if a Player has permission in a certain location
   * @param islandPerm IslandPerm based on the action from the user
   * @param location Location in the world (this is ALWAYS) the Skyblock World
   * @return Boolean, if they have permission or not.
   */
  public boolean hasPerm(IslandPerm islandPerm, Location<World> location) {
    Island island = Island.getIslandAt(location);
    if(Island.getIslandAt(location) == null) return false;
    return hasPerm(islandPerm, island);
  }

  /**
   * Checks if a user has permission for a certain Island
   * @param islandPerm IslandPerm based on the action of the user
   * @param island Island in question
   * @return Boolean, if they have permission or not.
   */
  public boolean hasPerm(IslandPerm islandPerm, Island island) {
    if(island == null) return false;

    IslandPermissionLevel islandPermissionLevel = island.getIslandPermissions().get(islandPerm);
    int islandPermissionPosition = islandPermissionLevel.getPosition();

    if(islandPermissionLevel == IslandPermissionLevel.NONE) return true;
    if(islandPermissionLevel == IslandPermissionLevel.COOP && island.isCoop(getPlayerUUID())) return true;
    if(getIsland() == null) return false;
    if(island == getIsland()) {
      return islandPermissionPosition <= getIslandRole().getPosition();
    }
    return false;
  }

  /**
   * Check if the Specified Player is in an Island
   * @return boolean
   */
  public boolean isInIsland() {
    return !island_uuid.equals(new UUID(0L, 0L));
  }

  /**
   * Checks if a Player is in a specified island
   * @param island Island in question
   * @return If they are in the island or not
   */
  public boolean isInIsland(Island island) {
    return island.getIslandUUID() == getIslandUUID();
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

    AsyncUtil.async(() -> {
      save(this);
    });
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
    return this.PLAYER_UUID;
  }

  /**
   * @return The Players' Island Role
   */
  public IslandPermissionLevel getIslandRole() {
    return IslandPermissionLevel.fromString(this.island_role);
  }

  /**
   * Get a Sponge Player instance from SPlayer object
   * @return Player (Sponge)
   */
  public Player getSpongePlayer() {
    return Sponge.getServer().getPlayer(getPlayerUUID()).get();
  }

  /**
   * Checks if a Player's current location is at their island
   * @return true/false
   */
  public boolean atTheirIsland() {
    return Island.getIslandAt(getSpongePlayer().getLocation()) == getIsland();
  }

  public boolean isBanned(Island island) {
    return isBanned(island.getIslandUUID());
  }

  public boolean isBanned(UUID island) {
    return BanCache.isBanned(getPlayerUUID(), island);
  }

}
