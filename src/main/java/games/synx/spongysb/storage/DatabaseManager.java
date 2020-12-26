package games.synx.spongysb.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.conf.ConfSettings;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class DatabaseManager implements IDatabase {

  private final Logger logger;

  public HikariDataSource dataSource;

  public DatabaseManager() {
    logger = SpongySB.get().getLogger();
    logger.info("Initialising Database Manager");

    logger.info("Loading Database Details");
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

    logger.info("Creating HikariDataSource");
    this.dataSource = new HikariDataSource(config);

    logger.info("Database has been loaded!");

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
    logger.info("Attempting to create default tables (if they do not exist)");
    try (Connection connection = getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.CREATE_ISLANDS_TABLE);
      PreparedStatement preparedStatement2 = connection.prepareStatement(Statements.CREATE_PLAYERS_TABLE);
      PreparedStatement preparedStatement3 = connection.prepareStatement(Statements.CREATE_GRID_TABLE);

      preparedStatement.execute();
      preparedStatement2.execute();
      preparedStatement3.execute();
      logger.info("Successfully created default tables (if they do not exist)");

    } catch (SQLException e) {

      e.printStackTrace();
    }
  }


}
