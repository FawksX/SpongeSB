package games.synx.spongysb.generation;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.world.World;

import java.util.concurrent.TimeUnit;

public class WorldManager {

  public static WorldManager get() {
    return instance;
  }

  public static WorldManager instance;

  private final World islandWorld;

  public WorldManager() {
    instance = this;

    SpongySB.get().getLogger().info("Beginning World Manager Initialisation");
    islandWorld = getIslandWorld();
  }

  public World getIslandWorld() {
    SpongySB.get().getLogger().info("Getting Island World");
    if(!Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).isPresent()) {
      SpongySB.get().getLogger().info("Island World does not exist!");
      createWorld();
      return Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).get();
    }
    SpongySB.get().getLogger().info("Island World found!");
    return Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).get();

  }


  public void createWorld() {

    SpongySB.get().getLogger().info("Creating Island World...");
    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "world create -p the_void " + ConfigManager.get().getConf().world.worldName);
  }


  // ----------------------------------------------- //
  // GETTERS AND SETTERS
  // ----------------------------------------------- //

  public World getWorld() {
    return islandWorld;
  }


}
