package games.synx.spongysb.listeners;

import games.synx.spongysb.SpongySB;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;

public class ListenerManager {

  private Logger logger = SpongySB.get().getLogger();

  public ListenerManager() {

    logger.info("Registering Listener Manager!");

    Sponge.getEventManager().registerListeners(SpongySB.get().getPluginContainer(), new PlayerJoinServerListener());
    Sponge.getEventManager().registerListeners(SpongySB.get().getPluginContainer(), new PlayerGuard());

    Sponge.getEventManager().registerListeners(SpongySB.get().getPluginContainer(), new PlayerCacheDisconnectListener());

  }

}
