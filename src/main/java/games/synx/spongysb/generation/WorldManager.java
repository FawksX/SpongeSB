package games.synx.spongysb.generation;

import games.synx.pscore.manager.AbstractManager;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Conf;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class WorldManager extends AbstractManager implements IManager {

  public static WorldManager get() {
    return instance;
  }

  private static WorldManager instance;

  private final Conf.ConfSettings conf;

  private final World islandWorld;

  public WorldManager() {
    super(SpongySB.get().getLogger());
    this.conf = ConfigManager.get().getConf();
    instance = this;

    islandWorld = getIslandWorld();
  }

  private World getIslandWorld() {
    getLogger().info("Getting island World");
    if(!Sponge.getServer().getWorld(conf.world.worldName).isPresent()) {
      createWorld();
      return Sponge.getServer().getWorld(conf.world.worldName).get();
    }
    return Sponge.getServer().getWorld(conf.world.worldName).get();

  }

  private void createWorld() {
    getLogger().info("Creating island World...");
    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "world create -m sponge:void " + conf.world.worldName);
  }

  // ----------------------------------------------- //
  // GETTERS AND SETTERS
  // ----------------------------------------------- //

  public World getWorld() {
    return islandWorld;
  }

  public Location<World> getServerSpawn() {
    return new Location<World>(Sponge.getServer().getWorld("world").get(), conf.spawnWorld.x, conf.spawnWorld.y, conf.spawnWorld.z);
  }


}
