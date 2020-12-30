package games.synx.spongysb.objects;

import games.synx.spongysb.SpongySB;
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
  private UUID player_uuid;
  private String island_role;
  private boolean island_bypass;

  private SPlayer(UUID island_uuid, UUID player_uuid, String island_role, boolean island_bypass) {
    this.island_uuid = island_uuid;
    this.player_uuid = player_uuid;
    this.island_role = island_role;
    this.island_bypass = island_bypass;
  }

  public static SPlayer get(Player player) {
    return get(player.getUniqueId());
  }

  public static SPlayer get(UUID uuid) {

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_PLAYER)) {

      preparedStatement.setString(1, uuid.toString());
      ResultSet rs = preparedStatement.executeQuery();
      rs.next();

      return new SPlayer(
          UUID.fromString(rs.getString("island_uuid")),
          uuid,
          rs.getString("island_role"),
          rs.getBoolean("admin_bypass")
      );

    } catch (SQLException e) {
      SpongySB.get().getLogger().error("Could not fetch user, did something go wrong?");
      e.printStackTrace();
      return null;
    }

  }

  public void setIsland(Island island) {

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.PLAYER_SET_ISLAND_UUID);) {

      preparedStatement.setString(1, island.getIslandUUID().toString());
      preparedStatement.setString(2, getPlayerUUID().toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void setIslandRole(IslandPermissionLevel islandPermissionLevel) {

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.PLAYER_SET_ISLAND_ROLE);) {

      preparedStatement.setString(1, islandPermissionLevel.toString());
      preparedStatement.setString(2, getPlayerUUID().toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
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

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(Statements.PLAYER_SET_ADMIN_BYPASSED);) {

      preparedStatement.setBoolean(1, bypassed);
      preparedStatement.setString(2, getPlayerUUID().toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

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
