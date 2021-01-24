package games.synx.spongysb.commands;

import games.synx.pscore.command.AbstractPSCommand;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Conf;
import games.synx.spongysb.config.configs.Messages;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;

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

  public void postEvent(Event event) {
    Sponge.getEventManager().post(event);
  }


}
