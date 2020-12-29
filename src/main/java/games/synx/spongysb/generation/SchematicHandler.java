package games.synx.spongysb.generation;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.sponge.SpongeWorldEdit;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.conf.Conf;
import javafx.util.Callback;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class SchematicHandler {

  private final File file; // Schematic

  public SchematicHandler(File file) {
    this.file = file;
  }

  public void pasteSchematicAsync(Location<World> centerLoc, Player player) {
    try {

      com.sk89q.worldedit.world.World spongeWorld = SpongeWorldEdit.inst().getWorld(centerLoc.getExtent());

      Schematic schem = FaweAPI.load(file);
      Clipboard clipboard = schem.getClipboard();

      try {
        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(spongeWorld, -1);
        com.sk89q.worldedit.world.World world = SpongeWorldEdit.inst().getWorld(centerLoc.getExtent());
        Operation operation =
            new ClipboardHolder(clipboard, world.getWorldData())
                .createPaste(editSession, world.getWorldData()).to(BlockVector.toBlockPoint(centerLoc.getX(), centerLoc.getY(), centerLoc.getZ()))
                .ignoreAirBlocks(false)
                .build();

        Operations.complete(operation);
      } catch (Throwable throwable) {
        SpongySB.get().getLogger().error("COULD NOT PASTE SCHEMATIC! SchematicHandler pasteSchematicAsync");
        throwable.printStackTrace();
        return;
      }

      handleMinMaxCalculation(centerLoc, player);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handleMinMaxCalculation(
      Location<World> centerLoc, Player player) {

    // TODO ADD IN ISLAND UPGRADE CONF TO GET DEFAULT PROTECTION RANGE (WHICH IS PROTECTIONRANGE = 300)

    int protectionRange = 300;
    int half = protectionRange / 2;

    Location<World> minLoc =
        new Location<World>(
            centerLoc.getExtent(), centerLoc.getBlockX() - half, 0, centerLoc.getBlockZ() - half);
    Location<World> maxLoc =
        new Location<World>(
            centerLoc.getExtent(), centerLoc.getBlockX() + half, 254, centerLoc.getBlockZ() + half);

  }


}