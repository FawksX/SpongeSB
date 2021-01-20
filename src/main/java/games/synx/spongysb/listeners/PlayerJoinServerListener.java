package games.synx.spongysb.listeners;

import games.synx.pscore.util.AsyncUtil;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.cache.BanCache;
import games.synx.spongysb.cache.PlayerCache;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;
import games.synx.spongysb.util.IslandUtil;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerJoinServerListener {

    private final Logger logger = SpongySB.get().getLogger();

    public PlayerJoinServerListener() {
        logger.info("Registering PlayerJoinServerListener");
    }

    @Listener
    public void clientConnectCacheSave(ClientConnectionEvent.Join event) {

        Player player = (Player) event.getSource();

        AsyncUtil.async(() -> {

            SPlayer sPlayer = SPlayer.fetch(player.getUniqueId());

            // If player has no data, make their object.
            if (sPlayer == null) {
                try (Connection connection = DatabaseManager.get().getConnection();
                     PreparedStatement stmt = connection.prepareStatement(Statements.INSERT_PLAYER)) {

                    stmt.setString(1, player.getUniqueId().toString());
                    stmt.setString(2, String.valueOf(new UUID(0L, 0L)));
                    stmt.setBoolean(3, false);

                    stmt.executeUpdate();

                } catch (SQLException e) {
                    SpongySB.get().getLogger().error("Something went wrong with your database!");
                    e.printStackTrace();
                }

            }

            PlayerCache.add(SPlayer.fetch(player.getUniqueId()));

            try(Connection connection = DatabaseManager.get().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_BANS_OF_PLAYER)) {
                preparedStatement.setString(1, player.getUniqueId().toString());
                ResultSet rs = preparedStatement.executeQuery();

                while(rs.next()) {
                    BanCache.add(player.getUniqueId(), UUID.fromString(rs.getString("island_uuid")));
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }

            IslandUtil.changeBorder(player, player.getLocation());
        });

        // If they are in the island world, check to see if they're allowed to be there
        if (!SPlayer.get(player).hasPerm(IslandPerm.ENTRY, player.getLocation())) {
            player.setLocationSafely(WorldManager.get().getServerSpawn());
        }

    }


}
