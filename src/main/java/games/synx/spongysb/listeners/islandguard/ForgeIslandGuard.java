package games.synx.spongysb.listeners.islandguard;

import games.synx.spongysb.objects.enums.IslandPerm;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

public class ForgeIslandGuard extends AbstractIslandGuard {

    @SubscribeEvent
    public void onBucketInteract(PlayerInteractEvent event) {
        if(event.getItemStack().getItem() != Items.LAVA_BUCKET || event.getItemStack().getItem() != Items.WATER_BUCKET) return;
        if(passesGenericIslandChecks((Player) event.getEntityPlayer(), IslandPerm.PLACE)) return;

        event.setCanceled(true);
    }
}
