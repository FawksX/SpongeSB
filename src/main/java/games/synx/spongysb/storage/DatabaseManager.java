package games.synx.spongysb.storage;

import games.synx.pscore.manager.AbstractManager;
import games.synx.pscore.manager.IManager;
import games.synx.pscore.storage.HikariSource;
import games.synx.pscore.storage.IDatabase;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Conf;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    getLogger().info("Loading Database Details");
    Conf.ConfSettings.Database databaseConf = ConfigManager.get().getConf().database;

    database = new HikariSource(
            databaseConf.address,
            databaseConf.port,
            databaseConf.database,
            databaseConf.username,
            databaseConf.password);

    createTables();
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
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.CREATE_ISLANDS_TABLE);
      PreparedStatement preparedStatement2 = connection.prepareStatement(Statements.CREATE_PLAYERS_TABLE);
      PreparedStatement preparedStatement3 = connection.prepareStatement(Statements.CREATE_GRID_TABLE);
      PreparedStatement preparedStatement4 = connection.prepareStatement(Statements.CREATE_ISLAND_UPGRADES_TABLE);
      PreparedStatement preparedStatement5 = connection.prepareStatement(Statements.CREATE_ISLAND_PERMISSIONS_TABLE);

      preparedStatement.execute();
      preparedStatement2.execute();
      preparedStatement3.execute();
      preparedStatement4.execute();
      preparedStatement5.execute();
    } catch (SQLException e) {

      e.printStackTrace();
    }
  }


}
