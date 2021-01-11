package games.synx.spongysb.config;

import games.synx.pscore.config.impl.AbstractConfigManager;
import games.synx.pscore.config.impl.IConfigManager;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.configs.Conf;
import games.synx.spongysb.config.configs.GUI;
import games.synx.spongysb.config.configs.Messages;
import games.synx.spongysb.config.configs.Upgrades;

import java.io.IOException;

public class ConfigManager extends AbstractConfigManager implements IManager, IConfigManager {

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
      // upgrades = new Upgrades(getFilePath("upgrades.yml"));
      gui = new GUI(getFilePath("guis.json"));

    } catch (IOException e) {
      getLogger().error("Could not instantiate a config!");
      e.printStackTrace();
    }
  }

  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //

  public Conf.ConfSettings getConf() {
    return this.conf.getSettings();
  }

  public Messages.MessageSettings getMessages() {
    return this.message.getSettings();
  }

  public Upgrades.UpgradeSettings getUpgrades() {
    return this.upgrades.getSettings();
  }

  public GUI.GUISettings getGUIs() {
    return this.gui.getSettings();
  }

}
