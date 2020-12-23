package games.synx.spongysb.config;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.conf.Conf;
import games.synx.spongysb.config.conf.ConfSettings;
import games.synx.spongysb.config.messages.MessageSettings;
import games.synx.spongysb.config.messages.Messages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {

  public static ConfigManager get() {
    return instance;
  }

  private static ConfigManager instance;

  private Conf conf;
  private Messages message;

  private final Path confPath = Paths.get(SpongySB.get().getConfigDir() + File.separator + "conf.json");
  private final Path messagePath = Paths.get(SpongySB.get().getConfigDir() + File.separator + "messages.json");


  public ConfigManager() {
    instance = this;

    try {
      conf = new Conf(confPath);
      message = new Messages(messagePath);


    } catch (IOException e) {
      SpongySB.get().getLogger().error("Could not instantiate a config!");
      e.printStackTrace();

    }
  }


  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //

  public ConfSettings getConf() {
    return this.conf.getConf();
  }

  public MessageSettings getMessages() {
    return this.message.getMessage();
  }

}
