package games.synx.spongysb.storage;

public class Statements {

  /**
   * CREATING TABLES
   * USED IN games.synx.spongysb.storage.DatabaseManager
   */
  public static final String CREATE_ISLANDS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_islands (island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, leader_uuid VARCHAR(36) NOT NULL, island_name text NOT NULL, center_location text NOT NULL, banned_members longtext NOT NULL, home_location text NOT NULL)";
  public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_players (player_uuid VARCHAR(36) NOT NULL PRIMARY KEY, island_uuid VARCHAR(36) NOT NULL,  island_role text NOT NULL, admin_bypass boolean NOT NULL)";
  public static final String CREATE_GRID_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_grid (id VARCHAR(1) NOT NULL PRIMARY KEY, lastisland text NOT NULL)";
  public static final String CREATE_ISLAND_UPGRADES_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_upgrades (island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, island_size integer NOT NULL)";
  public static final String CREATE_ISLAND_INVITES_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_invites (island_uuid VARCHAR(36) NOT NULL, player_uuid VARCHAR(36) NOT NULL, invite_time bigint(19) NOT NULL)";

  // CREATING ISLAND/PLAYER ROWS
  public static final String INSERT_ISLAND = "REPLACE INTO spongysb_islands (island_uuid, leader_uuid, island_name, center_location, banned_members, home_location) VALUES(?,?,?,?,?,?)";
  public static final String INSERT_PLAYER = "REPLACE INTO spongysb_players (player_uuid, island_uuid, island_role, admin_bypass) VALUES(?,?,?,?)";

  // GETTING FULL PLAYER/ISLAND INFORMATION
  public static final String GET_PLAYER = "SELECT island_uuid, island_role, admin_bypass FROM spongysb_players WHERE player_uuid = ?";
  public static final String GET_ISLAND = "SELECT leader_uuid, island_name, center_location, banned_members, home_location FROM spongysb_islands WHERE island_uuid = ?";
  public static final String GET_ISLAND_FROM_LOCATION = "SELECT * FROM spongysb_islands WHERE center_location = ?";
  public static final String GET_ISLAND_UPGRADES = "SELECT (island_size) FROM spongysb_island_upgrades WHERE island_uuid = ?";

  public static final String GET_ISLAND_INVITE = "SELECT (invite_time) FROM spongysb_island_invites WHERE player_uuid = ? AND island_uuid = ?";

  public static final String GET_PLAYERS_IN_ISLAND = "SELECT (player_uuid) FROM spongysb_players WHERE island_uuid = ?";

  // PLAYER - SETTING INDIVIDUAL SECTIONS
  public static final String PLAYER_SET_ISLAND_UUID = "UPDATE spongysb_players SET island_uuid = ? WHERE player_uuid = ?";
  public static final String PLAYER_SET_ISLAND_ROLE = "UPDATE spongysb_players SET island_role = ? WHERE player_uuid = ?";
  public static final String PLAYER_SET_ADMIN_BYPASSED = "UPDATE spongysb_players SET admin_bypass = ? WHERE player_uuid = ?";

  // ISLAND - SETTING INDIVIDUAL SECTIONS
  public static final String UPDATE_ISLAND_INVITE = "REPLACE INTO spongysb_island_invites (island_uuid, player_uuid, invite_time) VALUES(?,?,?)";
  public static final String DELETE_ISLAND_INVITE = "DELETE FROM spongysb_island_invites WHERE island_uuid = ? AND player_uuid = ?";
  public static final String ISLAND_SET_NAME = "UPDATE spongysb_islands SET island_name = ? WHERE island_uuid = ?";

  // ISLAND - GETTING INDIVIDUAL SECTION
  public static final String GET_ISLAND_UUID = "SELECT (island_uuid) FROM spongysb_islands WHERE UPPER(island_name) = ?";

  // LAST GRID LOCATION BOLLOCKS
  public static final String GET_LAST_LOCATION = "SELECT * FROM spongysb_grid";
  public static final String UPDATE_LAST_GRID_ISLAND = "REPLACE INTO spongysb_grid (id, lastisland) VALUES(1,?);";



}
