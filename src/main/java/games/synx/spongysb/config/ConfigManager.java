package games.synx.spongysb.config;

import games.synx.pscore.config.exception.ConfigLoadException;
import games.synx.pscore.config.impl.AbstractConfigManager;
import games.synx.pscore.config.impl.IConfigManager;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.configs.Conf;
import games.synx.spongysb.config.configs.Messages;
import games.synx.spongysb.config.configs.Upgrades;
import games.synx.spongysb.config.configs.guis.PermGUI;
import games.synx.spongysb.config.configs.guis.SchemGUI;

public class ConfigManager extends AbstractConfigManager implements IManager, IConfigManager {

  public static ConfigManager get() {
    return instance;
  }

  private static ConfigManager instance;

  private IConfiguration conf;
  private IConfiguration message;
  private IConfiguration upgrades;

  private IConfiguration permGUI;
  private IConfiguration schemGUI;

  public ConfigManager() {
    super(SpongySB.get().getLogger(), SpongySB.get().getConfigDir());

    instance = this;

    try {
      conf = setupConfig(new Conf());
      message = setupConfig(new Messages());
      upgrades = setupConfig(new Upgrades());

      permGUI = setupConfig(new PermGUI());
      schemGUI = setupConfig(new SchemGUI());
    } catch (ConfigLoadException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //

  public Conf.ConfSettings getConf() {
    return (Conf.ConfSettings) this.conf.getSettings();
  }

  public Messages.MessageSettings getMessages() {
    return (Messages.MessageSettings) this.message.getSettings();
  }

  public Upgrades.UpgradeSettings getUpgrades() {
    return (Upgrades.UpgradeSettings) this.upgrades.getSettings();
  }

  public PermGUI.PermGUISettings getPermissionsGUI() {
    return (PermGUI.PermGUISettings) this.permGUI.getSettings();
  }

  public SchemGUI.SchemGUISettings getSchematicGUI() {
    return (SchemGUI.SchemGUISettings) this.schemGUI.getSettings();
  }

}
