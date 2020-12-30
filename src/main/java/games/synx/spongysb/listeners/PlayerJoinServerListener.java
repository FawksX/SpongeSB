package games.synx.spongysb.listeners;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.storage.Statements;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
  public void onPlayerJoin(ClientConnectionEvent.Join event) {

    logger.info("CHECKING IF PLAYER EXISTS IN PLAYERS DB");

    Player player = (Player) event.getSource();

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_PLAYER);
      preparedStatement.setString(1, player.getUniqueId().toString());
      ResultSet rs = preparedStatement.executeQuery();

      // Check if Player has no data
      if(!rs.next()) {

        PreparedStatement stmt = connection.prepareStatement(Statements.INSERT_PLAYER);
        stmt.setString(1, player.getUniqueId().toString());
        stmt.setString(2, String.valueOf(new UUID(0L, 0L)));
        stmt.setString(3, "");
        stmt.setBoolean(4, false);

        stmt.executeUpdate();
        connection.close();

      } else {
        logger.info("This player already exists in the database!");
        connection.close();
      }

    } catch (SQLException e) {
      SpongySB.get().getLogger().error("Something went wrong with your database!");
      e.printStackTrace();
    }

  }

}
