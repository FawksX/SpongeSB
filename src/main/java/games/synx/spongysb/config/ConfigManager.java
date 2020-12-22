package games.synx.spongysb.config;

import com.google.inject.Inject;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.conf.Conf;
import games.synx.spongysb.config.conf.ConfSettings;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {

  private Conf conf;

  private Path confPath = Paths.get(SpongySB.get().getConfigDir() + File.separator + "conf.json");


  public ConfigManager() {

    try {
      Conf conf = new Conf(confPath);

    } catch (IOException e) {
      SpongySB.get().getLogger().error("Could not instantiate a config!");
      e.printStackTrace();

    }
  }


  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //

  public Conf getConf() {
    return this.conf;
  }

}
