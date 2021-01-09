package games.synx.spongysb.config;

import games.synx.pscore.config.impl.AbstractConfigManager;
import games.synx.pscore.config.impl.IConfigManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.conf.Conf;
import games.synx.spongysb.config.conf.ConfSettings;
import games.synx.spongysb.config.gui.GUI;
import games.synx.spongysb.config.gui.GUISettings;
import games.synx.spongysb.config.messages.MessageSettings;
import games.synx.spongysb.config.messages.Messages;
import games.synx.spongysb.config.upgrades.UpgradeSettings;
import games.synx.spongysb.config.upgrades.Upgrades;

import java.io.IOException;

public class ConfigManager extends AbstractConfigManager implements IConfigManager {

  public static ConfigManager get() {
    return instance;
  }

  private static ConfigManager instance;

  private Conf conf;
  private Messages message;
  private Upgrades upgrades;
  private GUI gui;

  public ConfigManager() {
    super(SpongySB.get().getLogger(), SpongySB.get().getConfigDir());

    instance = this;

    try {
      conf = new Conf(getFilePath("conf.json"));
      message = new Messages(getFilePath("messages.json"));
     // upgrades = new Upgrades(upgradesPath); // disabled for now as it's not needed
      gui = new GUI(getFilePath("guis.json"));

    } catch (IOException e) {
      getLogger().error("Could not instantiate a config!");
      e.printStackTrace();

    }
  }

  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //

  public ConfSettings getConf() {
    return this.conf.getSettings();
  }

  public MessageSettings getMessages() {
    return this.message.getSettings();
  }

  public UpgradeSettings getUpgrades() {
    return this.upgrades.getSettings();
  }

  public GUISettings getGUIs() {
    return this.gui.getSettings();
  }

}
