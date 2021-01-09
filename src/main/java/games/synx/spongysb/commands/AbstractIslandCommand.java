package games.synx.spongysb.commands;

import games.synx.pscore.command.AbstractPSCommand;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.conf.ConfSettings;
import games.synx.spongysb.config.messages.MessageSettings;

public class AbstractIslandCommand extends AbstractPSCommand {

  public AbstractIslandCommand() {
    super(SpongySB.get().getLogger());
  }

  public ConfSettings getConf() {
    return ConfigManager.get().getConf();
  }

  public MessageSettings getMessages() {
    return ConfigManager.get().getMessages();
  }


}
