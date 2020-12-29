package games.synx.spongysb.commands;

import co.aikar.commands.BaseCommand;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.conf.ConfSettings;
import games.synx.spongysb.config.messages.MessageSettings;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

public class AbstractIslandCommand extends BaseCommand {

  private Logger logger = SpongySB.get().getLogger();

  public void msg(Player player, String message) {
    player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(message));
  }

  public ConfSettings getConf() {
    return ConfigManager.get().getConf();
  }

  public MessageSettings getMessages() {
    return ConfigManager.get().getMessages();
  }


  public Logger getLogger() {
    return this.logger;
  }

}
