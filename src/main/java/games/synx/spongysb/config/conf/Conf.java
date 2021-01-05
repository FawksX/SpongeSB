package games.synx.spongysb.config.conf;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public final class Conf extends AbstractConfiguration<ConfSettings> implements IConfiguration {

  public Conf(Path configFile) throws IOException {
    super(configFile, ConfSettings.class);
  }

}


