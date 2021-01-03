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

  public static SPlayer get(Player player) {
    return get(player.getUniqueId());
  }

  public static SPlayer get(UUID uuid) {
    return PlayerCache.get(uuid);
  }

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

  public void setIsland(Island island) {
    this.island_uuid = island.getIslandUUID();
  }

  public void setIsland(UUID uuid) {
    this.island_uuid = uuid;
  }

  public void setIslandRole(IslandPermissionLevel islandPermissionLevel) {

    this.island_role = islandPermissionLevel.toString();

  }

  // TODO WRITE LOGIC FOR ISLAND PERMISSIONS // RANKS
  public boolean hasPerm(SPlayer sPlayer, IslandPerm islandPerm, Location<World> world) {
    if(Island.getIslandAt(world) == null) {
      return false;
    }
    return sPlayer.getIsland().getIslandUUID().toString().equals(Island.getIslandAt(world).getIslandUUID().toString());
  }

  public boolean isInIsland() {
    return !island_uuid.equals(new UUID(0L, 0L));
  }

  public Boolean isBypassed() {
    return island_bypass;
  }

  public void setBypassed(Boolean bypassed) {
    this.island_bypass = bypassed;

  }

  public void removeFromIsland() {
    setIsland(new UUID(0L, 0L));
    setIslandRole(IslandPermissionLevel.NONE);
  }

  public Island getIsland() {
    return Island.get(island_uuid);
  }

  public UUID getIslandUUID() {
    return this.island_uuid;
  }

  public UUID getPlayerUUID() {
    return this.player_uuid;
  }

  public String getIslandRole() {
    return this.island_role;
  }

}
