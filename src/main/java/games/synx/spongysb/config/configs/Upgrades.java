package games.synx.spongysb.config.configs;

import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Upgrades extends AbstractConfiguration<Upgrades.UpgradeSettings> implements IConfiguration {

  public Upgrades(Path configFile) throws IOException {
    super(configFile, UpgradeSettings.class);
  }

  @ConfigSerializable
  public static class UpgradeSettings {

    @Setting
    public Map<Integer, Integer> size_upgrades = new HashMap<Integer, Integer>() {{
      put(1, 50);
      put(2, 100);
      put(3, 150);
      put(4, 200);
    }};



  }

}