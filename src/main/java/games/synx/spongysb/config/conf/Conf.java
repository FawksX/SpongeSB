package games.synx.spongysb.config.conf;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.nio.file.Path;

public final class Conf extends AbstractConfiguration implements IConfiguration {

  private static final ObjectMapper<ConfSettings> MAPPER;
  private ConfSettings conf;

  public Conf(Path configFile) throws IOException {
    super(configFile);
    setup();
  }

  static {
    try {
      MAPPER = ObjectMapper.forClass(ConfSettings.class);
    } catch (final ObjectMappingException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  @Override
  public void saveConfiguration(final Object configuration, final ConfigurationNode node) throws ObjectMappingException {
    MAPPER.bind((ConfSettings) configuration).serialize(node);
  }

  @Override
  public void setup() {
    try {

      // Loading
      this.conf = (ConfSettings) loadConfiguration(MAPPER, getRawNode());

      // Saving
      saveConfiguration(this.getConf(), getRawNode());
      saveRawNode();

    } catch (ObjectMappingException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //
  public ConfSettings getConf() {
    return conf;
  }

}


