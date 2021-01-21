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

        BlockSnapshot bto = e.getTransactions().get(0).getOriginal();
        boolean isLiquidMix = e.getContext().containsKey(EventContextKeys.LIQUID_MIX);

        if (!isLiquidMix && !bto.getLocation().isPresent() && bto.getState().getType() == BlockTypes.FLOWING_LAVA) {
            return;
        }

        Location<World> block = bto.getLocation().get();

        if(!GridManager.get().inWorld(block)) return;

        Island island = Island.getIslandAt(block);

        if(island == null) return;

        e.setCancelled(true);

        block.setBlockType(getRandomBlock(island));


    }

    private BlockType getRandomBlock(Island island) {

        Map<String, Double> islandRandomBlocks = ConfigManager.get().getUpgrades().oreGeneratorValues.get(island.getIslandGeneratorValue());

        BlockType block = Sponge.getRegistry().getType(BlockType.class, RandomSelector.weighted(islandRandomBlocks.entrySet(), Map.Entry::getValue)
                .next(ThreadLocalRandom.current())
                .getKey()).get();

        if(block == null) {
            return BlockTypes.COBBLESTONE;
        }
        return block;

    }


}
