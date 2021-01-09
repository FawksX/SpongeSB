package games.synx.spongysb.listeners;

import games.synx.pscore.util.AsyncUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.CoopCache;
import games.synx.spongysb.cache.PlayerCache;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class CacheDisconnectListener {

    public CacheDisconnectListener() {
        SpongySB.get().getLogger().info("Registering PlayerCache Disconnect Listener");
    }

    @Listener
    public void onPlayerDisconnect(ClientConnectionEvent.Disconnect event) {

        SPlayer sPlayer = PlayerCache.get(event.getTargetEntity().getUniqueId());

        AsyncUtil.async(() -> {
            SPlayer.save(sPlayer);
        });

        CoopCache.removeIfPresent(event.getTargetEntity().getUniqueId());
        PlayerCache.remove(event.getTargetEntity().getUniqueId());

    }

}
