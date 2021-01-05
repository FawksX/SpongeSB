package games.synx.spongysb.config.messages;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public class Messages extends AbstractConfiguration<MessageSettings> implements IConfiguration {

  public Messages(Path configFile) throws IOException {
    super(configFile, MessageSettings.class);
  }
}
