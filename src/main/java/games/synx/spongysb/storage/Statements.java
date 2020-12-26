package games.synx.spongysb.storage;

public class Statements {

  public static final String CREATE_ISLANDS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_islands (island_uuid VARCHAR(36) PRIMARY KEY, leader_uuid VARCHAR(36), island_name text, center_location text, member_amount integer, banned_members longtext)";
  public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_players (island_uuid VARCHAR(36) PRIMARY KEY, player_uuid VARCHAR(36), player_ign text, island_role text)";
  public static final String CREATE_GRID_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_grid (lastisland text)";


}
