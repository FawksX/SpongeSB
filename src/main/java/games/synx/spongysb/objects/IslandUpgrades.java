package games.synx.spongysb.objects;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.storage.Statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class IslandUpgrades {

  private Island island;
  private int islandsize_level;

  private IslandUpgrades(Island island, int islandsize_level) {

    this.island = island;
    this.islandsize_level = islandsize_level;

  }

  public static IslandUpgrades get(UUID islandUUID) {
    return get(Island.get(islandUUID));
  }

  public static IslandUpgrades get(Island island) {

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ISLAND_UPGRADES);
      preparedStatement.setString(1, island.getIslandUUID().toString());
      ResultSet rs = preparedStatement.executeQuery();
      rs.next();

      return new IslandUpgrades(
          island,
          rs.getInt("island_size")
      );

    } catch (SQLException e) {
      SpongySB.get().getLogger().error("Could not fetch island, did something go wrong?");
      e.printStackTrace();
      return null;
    }

  }

  public int getIslandSize() {
    return ConfigManager.get().getUpgrades().size_upgrades.get(islandsize_level);
  }

}
