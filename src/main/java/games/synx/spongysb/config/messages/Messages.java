package games.synx.spongysb.config.messages;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.nio.file.Path;

public class Messages extends AbstractConfiguration implements IConfiguration {

  private static final ObjectMapper<MessageSettings> MAPPER;
  private MessageSettings message;

  public Messages(Path configFile) throws IOException {
    super(configFile);
    setup();
  }

  static {
    try {
      MAPPER = ObjectMapper.forClass(MessageSettings.class);
    } catch (final ObjectMappingException e) {
      throw new ExceptionInInitializerError(e);
    }
  }


  @Override
  public void saveConfiguration(final Object configuration, final ConfigurationNode node) throws ObjectMappingException {
    MAPPER.bind((MessageSettings) configuration).serialize(node);
  }

  @Override
  public void setup() {
    try {
      // Loading
      this.message = (MessageSettings) loadConfiguration(MAPPER, getRawNode());

      // Saving
      saveConfiguration(this.getMessage(), getRawNode());
      saveRawNode();

    } catch (ObjectMappingException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //
  public MessageSettings getMessage() {
    return message;
  }

}
