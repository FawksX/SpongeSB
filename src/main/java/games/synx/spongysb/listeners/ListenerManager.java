package games.synx.spongysb.listeners;

import games.synx.pscore.listener.impl.AbstractListenerManager;
import games.synx.pscore.listener.impl.IListenerManager;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.listeners.islandguard.VanillaIslandGuard;
import games.synx.spongysb.listeners.islandguard.ReforgedIslandGuard;

public class ListenerManager extends AbstractListenerManager implements IManager, IListenerManager {

  public ListenerManager() {
    super(SpongySB.get().getLogger(), SpongySB.get().getPluginContainer());

    registerSpongeEvent(new PlayerJoinServerListener());
    registerSpongeEvent(new VanillaIslandGuard());
    registerSpongeEvent(new PlayerCacheDisconnectListener());

    registerPixelmonEvent(new ReforgedIslandGuard());

  }

}
