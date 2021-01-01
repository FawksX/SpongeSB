package games.synx.spongysb;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = SpongeSBInfo.ID + "forge",
    name = SpongeSBInfo.NAME + "forge",
    version = SpongeSBInfo.VERSION,
    acceptableRemoteVersions = "*",
    dependencies = "required-after:nucleus")
public class SpongyForge {

  private static MinecraftServer server;
  private static SpongyForge instance;

  @Mod.EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    instance = this;
  }

  @Mod.EventHandler
  public void onServerStarting(FMLServerStartingEvent event) {
    server = event.getServer();
  }

  public static SpongyForge get() {
    return instance;
  }

  public static MinecraftServer getServer() {
    return server;
  }
}
