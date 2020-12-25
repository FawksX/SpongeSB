package games.synx.spongysb.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.conf.ConfSettings;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager implements IDatabase {

  private final Logger logger;

  public HikariDataSource dataSource;

  public DatabaseManager() {
    logger = SpongySB.get().getLogger();
    logger.info("Initialising Database Manager");

    logger.info("Loading Database Details");
    ConfSettings.Database databaseConf = ConfigManager.get().getConf().database;

    HikariConfig config = new HikariConfig();

    config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
    config.addDataSourceProperty("serverName", databaseConf.address);
    config.addDataSourceProperty("port", databaseConf.port);
    config.addDataSourceProperty("databaseName", databaseConf.database);
    config.setUsername(databaseConf.username);
    config.setPassword(databaseConf.password);

    logger.info("Creating HikariDataSource");
    this.dataSource = new HikariDataSource(config);

    logger.info("Database has been loaded!");
  }

  @Override
  public Connection getConnection() throws SQLException {
    return this.dataSource.getConnection();
  }

  @Override
  public void close() {
    this.dataSource.close();
  }


}
