package games.synx.spongysb.config.upgrades;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.nio.file.Path;

public class Upgrades extends AbstractConfiguration<UpgradeSettings> implements IConfiguration {

  public Upgrades(Path configFile) throws IOException {
    super(configFile, UpgradeSettings.class);
  }

}