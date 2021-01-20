package games.synx.spongysb.storage;

import games.synx.pscore.manager.AbstractManager;
import games.synx.pscore.manager.IManager;
import games.synx.pscore.storage.HikariSource;
import games.synx.pscore.storage.IDatabase;
import games.synx.pscore.util.AsyncUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Conf;

import java.sql.Connection;
import java.sql.SQLException;
public class DatabaseManager extends AbstractManager implements IManager, IDatabase {

  public static DatabaseManager get() {
    return instance;
  }

  private static DatabaseManager instance;

  public HikariSource database;

  public DatabaseManager() {
    super(SpongySB.get().getLogger());
    instance = this;

    Conf.ConfSettings.Database databaseConf = ConfigManager.get().getConf().database;

    database = new HikariSource(
            databaseConf.address,
            databaseConf.port,
            databaseConf.database,
            databaseConf.username,
            databaseConf.password);

    AsyncUtil.async(this::createTables);
  }

  @Override
  public Connection getConnection() throws SQLException {
    return this.database.getConnection();
  }

  @Override
  public void close() {
    this.database.close();
  }


  private void createTables() {
    try (Connection connection = getConnection()) {
      connection.prepareStatement(Statements.CREATE_ISLANDS_TABLE).executeUpdate();
      connection.prepareStatement(Statements.CREATE_PLAYERS_TABLE).executeUpdate();
      connection.prepareStatement(Statements.CREATE_GRID_TABLE).executeUpdate();
      connection.prepareStatement(Statements.CREATE_ISLAND_UPGRADES_TABLE).executeUpdate();
      connection.prepareStatement(Statements.CREATE_ISLAND_PERMISSIONS_TABLE).executeUpdate();
      connection.prepareStatement(Statements.CREATE_BANS_TABLE).executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
