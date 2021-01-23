package games.synx.spongysb.commands.common.impl;

import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Conf;
import games.synx.spongysb.config.configs.Messages;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

public class AbstractCommandCommon {

    protected static Conf.ConfSettings getConf() {
        return ConfigManager.get().getConf();
    }

    protected static Messages.MessageSettings getMessages() {
        return ConfigManager.get().getMessages();
    }

    protected static void postEvent(Event event) {
        Sponge.getEventManager().post(event);
    }

    protected static void msg(Player player, String message) {
        MessageUtil.msg(player, message);
    }

    protected static void msg(Player player, String message, Object... replacements) {
        MessageUtil.msg(player, message, replacements);
    }

    protected static Logger getLogger() {
        return SpongySB.get().getLogger();
    }

}
