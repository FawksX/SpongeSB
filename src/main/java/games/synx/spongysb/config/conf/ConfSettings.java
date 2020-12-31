package games.synx.spongysb.config.conf;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ConfSettings {

  @Setting
  public World world = new World();

  @Setting
  public Database database = new Database();

  @Setting
  public SpawnWorld spawnWorld = new SpawnWorld();

  @Setting
  public long invite_timeout_in_seconds = 900;

  /**
   * Configuration Section Dedicated to MySQL Connections
   * The only place where this is referenced is games.synx.spongysb.storage.DatabaseManager
   */
  @ConfigSerializable
  public static class Database {

    @Setting
    public String address = "london.enchantmc.com";

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

    @Setting
    public int island_paste_height = 100;

  }

  @ConfigSerializable
  public static class SpawnWorld {

    @Setting
    public String world = "world";

    @Setting
    public int x = 0;

    @Setting
    public int y = 100;

    @Setting
    public int z = 0;

  }

}