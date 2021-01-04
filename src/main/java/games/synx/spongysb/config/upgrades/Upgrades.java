package games.synx.spongysb.config.upgrades;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.nio.file.Path;

public class Upgrades extends AbstractConfiguration implements IConfiguration {

  private static final ObjectMapper<UpgradeSettings> MAPPER;
  private UpgradeSettings upgrades;

  public Upgrades(Path configFile) throws IOException {
    super(configFile);
    setup();
  }

  static {
    try {
      MAPPER = ObjectMapper.forClass(UpgradeSettings.class);
    } catch (final ObjectMappingException e) {
      throw new ExceptionInInitializerError(e);
    }
  }


  @Override
  public void saveConfiguration(final Object configuration, final ConfigurationNode node) throws ObjectMappingException {
    MAPPER.bind((UpgradeSettings) configuration).serialize(node);
  }

  @Override
  public void setup() {
    try {
      // Loading
      this.upgrades = (UpgradeSettings) loadConfiguration(MAPPER, getRawNode());

      // Saving
      saveConfiguration(this.getUpgrades(), getRawNode());
      saveRawNode();

    } catch (ObjectMappingException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //
  public UpgradeSettings getUpgrades() {
    return upgrades;
  }

}