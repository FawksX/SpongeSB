package games.synx.spongysb.commands;

import games.synx.pscore.command.AbstractPSCommand;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Conf;
import games.synx.spongysb.config.configs.Messages;

public class AbstractIslandCommand extends AbstractPSCommand {

  public AbstractIslandCommand() {
    super(SpongySB.get().getLogger());
  }

  public Conf.ConfSettings getConf() {
    return ConfigManager.get().getConf();
  }

  public Messages.MessageSettings getMessages() {
    return ConfigManager.get().getMessages();
  }


}
