package games.synx.spongysb.storage;

public class Statements {

  /**
   * CREATING TABLES
   * USED IN games.synx.spongysb.storage.DatabaseManager
   */
  public static final String CREATE_ISLANDS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_islands (island_uuid VARCHAR(36) PRIMARY KEY, leader_uuid VARCHAR(36), island_name text, center_location text, member_amount integer, banned_members longtext)";
  public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_players (player_uuid VARCHAR(36) PRIMARY KEY, island_uuid VARCHAR(36),  island_role text)";
  public static final String CREATE_GRID_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_grid (lastisland text)";

  // CREATING ISLAND/PLAYER ROWS
  public static final String INSERT_PLAYER = "REPLACE INTO spongysb_players (player_uuid, island_uuid, island_role) VALUES(?,?,?)";

  // GETTING FULL PLAYER/ISLAND INFORMATION
  public static final String GET_PLAYER = "SELECT island_uuid, island_role FROM spongysb_players WHERE player_uuid = ?";
  public static final String GET_ISLAND = "SELECT (island_uuid, leader_uuid, island_name, center_location, member_amount, banned_members) FROM spongysb_islands WHERE island_uuid=?";

}
