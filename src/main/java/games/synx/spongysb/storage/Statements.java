package games.synx.spongysb.storage;

public class Statements {

  /**
   * CREATING TABLES
   * USED IN games.synx.spongysb.storage.DatabaseManager
   */
  public static final String CREATE_ISLANDS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_islands (island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, leader_uuid VARCHAR(36) NOT NULL, island_name text NOT NULL, center_location text NOT NULL, home_location text NOT NULL)";
  public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_players (player_uuid VARCHAR(36) NOT NULL PRIMARY KEY, island_uuid VARCHAR(36) NOT NULL,  island_role text NOT NULL)";
  public static final String CREATE_GRID_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_grid (id VARCHAR(1) NOT NULL PRIMARY KEY, lastisland text NOT NULL)";
  public static final String CREATE_COOPS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_coops (player_uuid VARCHAR(36) NOT NULL, island_uuid VARCHAR(36) NOT NULL)";
  public static final String CREATE_ISLAND_UPGRADES_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_upgrades (island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, island_size integer NOT NULL)";

  // CREATING ISLAND/PLAYER ROWS
  public static final String INSERT_ISLAND = "REPLACE INTO spongysb_islands (island_uuid, leader_uuid, island_name, center_location, home_location) VALUES(?,?,?,?,?)";
  public static final String INSERT_PLAYER = "REPLACE INTO spongysb_players (player_uuid, island_uuid, island_role) VALUES(?,?,?)";

  // GETTING FULL PLAYER/ISLAND INFORMATION
  public static final String GET_ALL_ISLANDS = "SELECT * FROM spongysb_islands";
  public static final String GET_ALL_COOPS = "SELECT * FROM spongysb_coops";

  public static final String SAVE_ALL_ISLANDS = "UPDATE spongysb_islands SET leader_uuid = ?, island_name = ?, center_location = ?, home_location = ? WHERE island_uuid = ?";
  public static final String TRUNCATE_COOPS = "TRUNCATE spongysb_coops";
  public static final String SAVE_ALL_COOPS = "REPLACE INTO spongysb_coops (player_uuid, island_uuid) VALUES(?,?)";


  public static final String GET_PLAYER = "SELECT island_uuid, island_role FROM spongysb_players WHERE player_uuid = ?";
  public static final String GET_ISLAND = "SELECT leader_uuid, island_name, center_location, home_location FROM spongysb_islands WHERE island_uuid = ?";
  public static final String GET_ISLAND_UPGRADES = "SELECT (island_size) FROM spongysb_island_upgrades WHERE island_uuid = ?";

  public static final String GET_PLAYERS_IN_ISLAND = "SELECT (player_uuid) FROM spongysb_players WHERE island_uuid = ?";

  // PLAYER - SETTING INDIVIDUAL SECTIONS

  public static final String PLAYER_QUIT_UPDATE = "UPDATE spongysb_players SET island_uuid = ?, island_role = ? WHERE player_uuid = ?";

  // LAST GRID LOCATION BOLLOCKS
  public static final String GET_LAST_LOCATION = "SELECT * FROM spongysb_grid";
  public static final String UPDATE_LAST_GRID_ISLAND = "REPLACE INTO spongysb_grid (id, lastisland) VALUES(1,?);";



}
