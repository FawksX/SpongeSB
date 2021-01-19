package games.synx.spongysb.listeners.islandguard;

import com.pixelmonmod.pixelmon.api.events.PixelmonSendOutEvent;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.objects.enums.IslandPerm;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ReforgedIslandGuard extends AbstractIslandGuard {

    public ReforgedIslandGuard() {
        SpongySB.get().getLogger().info("Registering PixelmonIslandGuard");
    }

    /**
     * Checks if Player is allowed to send out their pokemon on an island
     * @param event PixelmonSendOutEvent (Pixelmon Reforged)
     */
    @SubscribeEvent
    public void onPokemonOutEvent(PixelmonSendOutEvent event) {
        if(passesGenericIslandChecks(event.player, IslandPerm.SEND_OUT_POKEMON)) return;
        event.setCanceled(true);

    }

}
