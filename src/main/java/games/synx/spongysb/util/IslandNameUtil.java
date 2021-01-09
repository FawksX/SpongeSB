package games.synx.spongysb.util;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.storage.DatabaseManager;
import games.synx.spongysb.storage.Statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IslandNameUtil {

  public static boolean isIslandNameTaken(String name) {

    try(Connection connection = DatabaseManager.get().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Statements.GET_ISLAND_UUID)) {

      preparedStatement.setString(1, name.toUpperCase());
      ResultSet rs = preparedStatement.executeQuery();

      if(rs.next()) {
        return true;
      }

      return false;
    } catch (SQLException e) {
      // Returning true here so that the island name is not accidently duplicated
      e.printStackTrace();
      return true;
    }
  }

}
