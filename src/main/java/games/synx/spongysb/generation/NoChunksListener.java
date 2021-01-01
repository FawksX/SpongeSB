package games.synx.spongysb.generation;

import games.synx.spongysb.config.ConfigManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.world.GenerateChunkEvent;

public class NoChunksListener {

  @Listener
  public void noChunkyPlz(GenerateChunkEvent.Pre event) {
    if(event.getChunkPreGenerate().getWorldProperties().getWorldName() == ConfigManager.get().getConf().world.worldName) {
      event.set
    }
  }
}
