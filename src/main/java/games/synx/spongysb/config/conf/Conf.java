package games.synx.spongysb.config.conf;


import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public final class Conf extends AbstractConfiguration<ConfSettings> implements IConfiguration {

  public Conf(Path configFile) throws IOException {
    super(configFile, ConfSettings.class);
  }

}


