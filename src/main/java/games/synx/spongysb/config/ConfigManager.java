package games.synx.spongysb.config;

import games.synx.pscore.config.impl.AbstractConfigManager;
import games.synx.pscore.config.impl.IConfigManager;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.configs.Conf;
import games.synx.spongysb.config.configs.Messages;
import games.synx.spongysb.config.configs.Upgrades;
import games.synx.spongysb.config.configs.guis.PermGUI;
import games.synx.spongysb.config.configs.guis.SchemGUI;

import java.io.IOException;

public class ConfigManager extends AbstractConfigManager implements IManager, IConfigManager {

  public static ConfigManager get() {
    return instance;
  }

  private static ConfigManager instance;

  private Conf conf;
  private Messages message;
  private Upgrades upgrades;

  private PermGUI permGUI;
  private SchemGUI schemGUI;

  public ConfigManager() {
    super(SpongySB.get().getLogger(), SpongySB.get().getConfigDir());

    instance = this;

    try {
      conf = new Conf(getFilePath("conf.json"));
      message = new Messages(getFilePath("messages.json"));
      upgrades = new Upgrades(getFilePath("upgrades.json"));

      permGUI = new PermGUI(getGUIPath("permissions.json"));
      schemGUI = new SchemGUI(getGUIPath("createisland.json"));

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

  public PermGUI.PermGUISettings getPermissionsGUI() {
    return this.permGUI.getSettings();
  }

  public SchemGUI.SchemGUISettings getSchematicGUI() {
    return this.schemGUI.getSettings();
  }

}
