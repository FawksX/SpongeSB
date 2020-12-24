package games.synx.spongysb.config.conf;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ConfSettings {

  @Setting
  public World world = new World();

  @Setting
  public Database database = new Database();

  /**
   * Configuration Section Dedicated to MySQL Connections
   * The only place where this is referenced is games.synx.spongysb.storage.DatabaseManager
   */
  @ConfigSerializable
  public static class Database {

    @Setting
    public String address = "127.0.0.1";

    @Setting
    public int port = 3306;

    @Setting
    public String username = "FawksX";

    @Setting
    public String password = "password";

    @Setting
    public String database = "SpongySB";

  }

  @ConfigSerializable
  public static class World {

    @Setting
    public String worldName = "SpongeSkyblock";

    @Setting
    public int islandDistance = 600;

  }

}