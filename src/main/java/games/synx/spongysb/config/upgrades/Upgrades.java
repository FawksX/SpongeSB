package games.synx.spongysb.config.upgrades;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;
import games.synx.spongysb.config.messages.MessageSettings;
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
      GsonConfigurationLoader loader = GsonConfigurationLoader.builder().setPath(getConfigFile()).build();
      ConfigurationNode raw = loader.load();

      // Loading
      this.upgrades = (UpgradeSettings) loadConfiguration(MAPPER, raw);

      // Saving
      saveConfiguration(this.getUpgrades(), raw);
      loader.save(raw);

    } catch (IOException | ObjectMappingException e) {
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