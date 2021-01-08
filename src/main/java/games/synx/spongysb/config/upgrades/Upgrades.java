package games.synx.spongysb.config.upgrades;

import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public class Upgrades extends AbstractConfiguration<UpgradeSettings> implements IConfiguration {

  public Upgrades(Path configFile) throws IOException {
    super(configFile, UpgradeSettings.class);
  }

}