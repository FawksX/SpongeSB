package games.synx.spongysb.listeners;

import com.pixelmonmod.pixelmon.Pixelmon;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.listeners.islandguard.VanillaIslandGuard;
import games.synx.spongysb.listeners.islandguard.ReforgedIslandGuard;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;

public class ListenerManager {

  private Logger logger = SpongySB.get().getLogger();

  public ListenerManager() {

    logger.info("Registering Listener Manager!");

    registerSpongeEvent(new PlayerJoinServerListener());
    registerSpongeEvent(new VanillaIslandGuard());
    registerSpongeEvent(new PlayerCacheDisconnectListener());

    registerPixelmonEvent(new ReforgedIslandGuard());

  }

  public void registerPixelmonEvent(Object T) {
    registerForgeEvent(T);
    Pixelmon.EVENT_BUS.register(T);
  }

  public void registerForgeEvent(Object T) {
    MinecraftForge.EVENT_BUS.register(T);
  }

  public void registerSpongeEvent(Object T) {
    Sponge.getEventManager().registerListeners(SpongySB.get().getPluginContainer(), T);
  }

}
