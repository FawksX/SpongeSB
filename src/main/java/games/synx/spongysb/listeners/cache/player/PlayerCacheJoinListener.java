package games.synx.spongysb.listeners.cache.player;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.PlayerCache;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.storage.Statements;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerCacheJoinListener {

    public PlayerCacheJoinListener() {
        SpongySB.get().getLogger().info("Registering PlayerCache Join Listener");
    }



}
