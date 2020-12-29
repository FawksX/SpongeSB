package games.synx.spongysb.util;

import com.typesafe.config.ConfigException;
import games.synx.spongysb.SpongySB;
import games.synx.spongysb.storage.Statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UUIDUtil {

  public static UUID generateUniqueIslandUUID() {

    UUID uuid = UUID.randomUUID();

    try (Connection connection = SpongySB.get().getDatabaseManager().getConnection()) {

      PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ISLAND);
      preparedStatement.setString(1, uuid.toString());
      ResultSet rs = preparedStatement.executeQuery();

      if(!rs.next()) {
        return uuid;
      } else {
        return generateUniqueIslandUUID();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    throw new NullPointerException();

  }

}
