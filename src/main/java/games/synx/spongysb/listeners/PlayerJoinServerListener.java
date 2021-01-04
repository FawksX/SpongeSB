package games.synx.spongysb.listeners;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.PlayerCache;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.storage.Statements;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * TEMPORARY LISTENER
 * IS TO TEST IS DBS CAN READ/WRITE CORRECTLY
 *
 */
public class PlayerJoinServerListener {

  private Logger logger = SpongySB.get().getLogger();

  public PlayerJoinServerListener() {
    logger.info("Registering PlayerJoinServerListener");
  }

  @Listener
  public void clientConnectCacheSave(ClientConnectionEvent.Join event) {

    Player player = (Player) event.getSource();

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection();
         PreparedStatement stmt = connection.prepareStatement(Statements.INSERT_PLAYER);) {

      SPlayer sPlayer = SPlayer.fetch(player.getUniqueId());

      // If player has no data, make their object.
      if(sPlayer == null) {

        stmt.setString(1, player.getUniqueId().toString());
        stmt.setString(2, String.valueOf(new UUID(0L, 0L)));
        stmt.setBoolean(3, false);

        stmt.executeUpdate();

      } else {
        connection.close();
      }

      PlayerCache.add(SPlayer.fetch(player.getUniqueId()));

      if(!Island.getIslandAt(player.getLocation()).getIslandUUID().toString().equals(sPlayer.getIslandUUID().toString())) {
        player.setLocationSafely(WorldManager.get().getServerSpawn());
      }

    } catch (SQLException e) {
      SpongySB.get().getLogger().error("Something went wrong with your database!");
      e.printStackTrace();
    }

  }


}
