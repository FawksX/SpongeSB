package games.synx.spongysb.listeners;

import games.synx.pscore.util.RandomSelector;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.objects.Island;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class OreGeneratorListener {

    @Listener
    public void onBlockModify(ChangeBlockEvent.Modify e) {

        System.out.println("PRINTING 1");

        BlockSnapshot bto = e.getTransactions().get(0).getOriginal();
        boolean isLiquidMix = e.getContext().containsKey(EventContextKeys.LIQUID_MIX);

        if (!isLiquidMix && !bto.getLocation().isPresent() && bto.getState().getType() == BlockTypes.FLOWING_LAVA) {
            return;
        }

        System.out.println("PRINTING 2");

        Location<World> block = bto.getLocation().get();

        if(!GridManager.get().inWorld(block)) return;

        Island island = Island.getIslandAt(block);

        System.out.println("PRINTING 3");

        e.setCancelled(true);

        block.setBlockType(getRandomBlock(island));
        System.out.println("PRINTING 4");


    }

    private BlockType getRandomBlock(Island island) {

        System.out.println("PRINTING 5");

        Map<String, Double> islandRandomBlocks = ConfigManager.get().getUpgrades().oreGeneratorValues.get(island.getIslandGeneratorValue());

        System.out.println("PRINTING 6");

        return Sponge.getRegistry().getType(BlockType.class, RandomSelector.weighted(islandRandomBlocks.entrySet(), Map.Entry::getValue)
                .next(ThreadLocalRandom.current())
                .getKey()).get();

    }


}
