package games.synx.spongysb.config.conf;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ConfSettings {

  @Setting
  public World world = new World();

  @Setting
  public String debug = "false";


  @ConfigSerializable
  public static class World {

    @Setting
    public String worldPrefix = "SpongySB";

    @Setting
    public int testInt = 100;
  }

}