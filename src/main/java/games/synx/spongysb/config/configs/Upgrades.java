package games.synx.spongysb.config.configs;

import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Upgrades extends AbstractConfiguration<Upgrades.UpgradeSettings> implements IConfiguration {

  public Upgrades(Path configFile) throws IOException {
    super(configFile, UpgradeSettings.class);
  }

  @ConfigSerializable
  public static class UpgradeSettings {

    @Setting
    public Map<Integer, Integer> size_upgrades = Stream.of(new int[][] {
            {1, 100},
            {2, 200}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));



  }

}