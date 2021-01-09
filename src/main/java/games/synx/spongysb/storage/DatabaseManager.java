package games.synx.spongysb.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import games.synx.pscore.manager.AbstractManager;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.conf.ConfSettings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class DatabaseManager extends AbstractManager implements IManager, IDatabase {

  public static DatabaseManager get() {
    return instance;
  }

  private static DatabaseManager instance;

  public HikariDataSource dataSource;

  public DatabaseManager() {
    super(SpongySB.get().getLogger());
    instance = this;

    getLogger().info("Loading Database Details");
    ConfSettings.Database databaseConf = ConfigManager.get().getConf().database;

    HikariConfig config = new HikariConfig();

    config.setJdbcUrl("jdbc:mysql://" + databaseConf.address + ":" + databaseConf.port + "/" + databaseConf.database);
    config.addDataSourceProperty("user", databaseConf.username);
    config.addDataSourceProperty("password", databaseConf.password);
    config.addDataSourceProperty("cachePrepStmts", true);
    config.addDataSourceProperty("prepStmtCacheSize", 250);
    config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    config.addDataSourceProperty("useServerPrepStmts", true);
    config.addDataSourceProperty("cacheCallableStmts", true);
    config.addDataSourceProperty("alwaysSendSetIsolation", false);
    config.addDataSourceProperty("cacheServerConfiguration", true);
    config.addDataSourceProperty("elideSetAutoCommits", true);
    config.addDataSourceProperty("useLocalSessionState", true);
    config.addDataSourceProperty("characterEncoding","utf8");
    config.addDataSourceProperty("useUnicode","true");
    config.setConnectionTimeout(TimeUnit.SECONDS.toMillis(15));
    config.setLeakDetectionThreshold(TimeUnit.SECONDS.toMillis(10));
    config.setConnectionTestQuery("/* Ping */ SELECT 1");

    this.dataSource = new HikariDataSource(config);

    getLogger().info("Creating HikariDataSource");
    this.dataSource = new HikariDataSource(config);

    createTables();
  }

  @Override
  public Connection getConnection() throws SQLException {
    return this.dataSource.getConnection();
  }

  @Override
  public void close() {
    this.dataSource.close();
  }


  private void createTables() {
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.CREATE_ISLANDS_TABLE);
      PreparedStatement preparedStatement2 = connection.prepareStatement(Statements.CREATE_PLAYERS_TABLE);
      PreparedStatement preparedStatement3 = connection.prepareStatement(Statements.CREATE_GRID_TABLE);
      PreparedStatement preparedStatement4 = connection.prepareStatement(Statements.CREATE_ISLAND_UPGRADES_TABLE);

      preparedStatement.execute();
      preparedStatement2.execute();
      preparedStatement3.execute();
      preparedStatement4.execute();

    } catch (SQLException e) {

      e.printStackTrace();
    }
  }


}
