package games.synx.spongysb.generation;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.ConfigManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;


public class WorldManager {

  public static WorldManager get() {
    return instance;
  }

  public static WorldManager instance;

  private final World islandWorld;

  public WorldManager() {
    instance = this;

    SpongySB.get().getLogger().info("Registering World Manager");
    islandWorld = getIslandWorld();
    SpongySB.get().getLogger().info("World Manager has been Registered!");
  }

  private World getIslandWorld() {
    SpongySB.get().getLogger().info("Getting island World");
    if(!Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).isPresent()) {
      SpongySB.get().getLogger().info("island World does not exist!");
      createWorld();
      return Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).get();
    }
    SpongySB.get().getLogger().info("island World found!");
    return Sponge.getServer().getWorld(ConfigManager.get().getConf().world.worldName).get();



  }


  private void createWorld() {

    SpongySB.get().getLogger().info("Creating island World...");
    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "world create -p the_void " + ConfigManager.get().getConf().world.worldName);
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
