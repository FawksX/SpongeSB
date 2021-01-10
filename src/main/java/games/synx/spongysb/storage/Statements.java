package games.synx.spongysb.storage;

public class Statements {

  /**
   * CREATING TABLES
   * USED IN games.synx.spongysb.storage.DatabaseManager
   */
  public static final String CREATE_ISLANDS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_islands (island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, leader_uuid VARCHAR(36) NOT NULL, island_name text NOT NULL, center_location text NOT NULL, home_location text NOT NULL, active boolean NOT NULL)";
  public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_players (player_uuid VARCHAR(36) NOT NULL PRIMARY KEY, island_uuid VARCHAR(36) NOT NULL,  island_role text NOT NULL)";
  public static final String CREATE_GRID_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_grid (id VARCHAR(1) NOT NULL PRIMARY KEY, lastisland text NOT NULL)";
  public static final String CREATE_ISLAND_UPGRADES_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_upgrades (island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, island_size integer NOT NULL)";

  public static final String CREATE_ISLAND_PERMISSIONS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_permissions (island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, " +
          "place text NOT NULL, break text NOT NULL, entry text NOT NULL, spawner_place text NOT NULL, spawner_break text NOT NULL, door text NOT NULL, button text NOT NULL, lever text NOT NULL, gate text NOT NULL, " +
          "container text NOT NULL, armorstand text NOT NULL, pressure_plate text NOT NULL, name text NOT NULL, description text NOT NULL, view_perms text NOT NULL, set_perms text NOT NULL, view_settings text NOT NULL, " +
          "set_settings text NOT NULL, invite text NOT NULL, kick text NOT NULL, deposit text NOT NULL, withdraw text NOT NULL, warp text NOT NULL, view_upgrades text NOT NULL, set_upgrades text NOT NULL, promote text NOT NULL," +
          "demote text NOT NULL, ban text NOT NULL, home text NOT NULL, is_lock text NOT NULL, set_home text NOT NULL, item_drop text NOT NULL, item_pickup text NOT NULL, hurt_animals text NOT NULL, hurt_mobs text NOT NULL," +
          "spawn_eggs text NOT NULL, breed text NOT NULL, ender_pearl text NOT NULL, music text NOT NULL, animals text NOT NULL, send_out_pokemon text NOT NULL)";

  // CREATING ISLAND/PLAYER ROWS
  public static final String INSERT_ISLAND = "REPLACE INTO spongysb_islands (island_uuid, leader_uuid, island_name, center_location, home_location, active) VALUES(?,?,?,?,?,?)";
  public static final String INSERT_PLAYER = "REPLACE INTO spongysb_players (player_uuid, island_uuid, island_role) VALUES(?,?,?)";

  // GETTING FULL PLAYER/ISLAND INFORMATION
  public static final String GET_ALL_ISLANDS = "SELECT * FROM spongysb_islands WHERE active = TRUE";
  public static final String SAVE_ALL_ISLANDS = "UPDATE spongysb_islands SET leader_uuid = ?, island_name = ?, center_location = ?, home_location = ?, active = ? WHERE island_uuid = ?";

  public static final String SAVE_ISLAND_PERMISSIONS = "REPLACE INTO spongysb_island_permissions (island_uuid, place, break, entry, spawner_place, spawner_break, door, button, lever, gate, container, armorstand, pressure_plate, name, description, view_perms, set_perms, view_settings, set_settings, invite, kick, deposit, withdraw, warp, view_upgrades, set_upgrades, promote, demote, ban, home, is_lock, set_home, item_drop, item_pickup, hurt_animals, hurt_mobs, spawn_eggs, breed, ender_pearl, music, animals, send_out_pokemon) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  public static final String GET_ISLAND_PERMISSIONS_TABLE = "SELECT * FROM spongysb_island_permissions WHERE island_uuid = ?";

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
