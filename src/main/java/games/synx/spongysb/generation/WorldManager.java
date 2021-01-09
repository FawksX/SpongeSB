package games.synx.spongysb.generation;

import games.synx.pscore.manager.AbstractManager;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class WorldManager extends AbstractManager implements IManager {

  public static WorldManager get() {
    return instance;
  }

  public static WorldManager instance;

  private final World islandWorld;

  public WorldManager() {
    super(SpongySB.get().getLogger());
    instance = this;

    islandWorld = getIslandWorld();
  }

  private World getIslandWorld() {
    getLogger().info("Getting island World");
    if(!Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).isPresent()) {
      createWorld();
      return Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).get();
    }
    return Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).get();

  }

  private void createWorld() {
    getLogger().info("Creating island World...");
    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "world create -m sponge:void " + ConfigManager.get().getConf().world.worldName);
  }

  // ----------------------------------------------- //
  // GETTERS AND SETTERS
  // ----------------------------------------------- //

  public World getWorld() {
    return islandWorld;
  }

  public Location<World> getServerSpawn() {
    return new Location<World>(Sponge.getServer().getWorld("world").get(), 0, 100, 0);
  }


}
