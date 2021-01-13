package games.synx.spongysb.config.configs;

import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.wrappers.IslandUpgradeWrapper;
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
    public SizeUpgrades sizeUpgrades = new SizeUpgrades();


    @ConfigSerializable
    public static class SizeUpgrades {

      Map<String, IslandUpgradeWrapper> tiers = new HashMap<String, IslandUpgradeWrapper>() {{
        put("0", new IslandUpgradeWrapper());
        put("1", new IslandUpgradeWrapper());
      }};

    }


  }

}