package games.synx.spongysb.storage;

public class Statements {

  /**
   * CREATING TABLES
   * USED IN games.synx.spongysb.storage.DatabaseManager
   */
  public static final String CREATE_ISLANDS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_islands (" +
          "island_uuid     VARCHAR(36) NOT NULL PRIMARY KEY, " +
          "leader_uuid     VARCHAR(36) NOT NULL, " +
          "island_name     text        NOT NULL, " +
          "center_location text        NOT NULL, " +
          "home_location   text        NOT NULL, " +
          "island_size     text        NOT NULL, " +
          "active          boolean     NOT NULL)";

  public static final String INSERT_ISLAND = "REPLACE INTO spongysb_islands (island_uuid, leader_uuid, island_name, center_location, home_location, island_size, active) VALUES(?,?,?,?,?,?,?)";

  public static final String GET_ALL_ISLANDS = "SELECT * FROM spongysb_islands WHERE active = TRUE";

  public static final String SAVE_ALL_ISLANDS = "UPDATE spongysb_islands SET leader_uuid = ?, island_name = ?, center_location = ?, home_location = ?, island_size = ?, active = ? WHERE island_uuid = ?";

  public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_players (" +
          "player_uuid VARCHAR(36) NOT NULL PRIMARY KEY, " +
          "island_uuid VARCHAR(36) NOT NULL,  " +
          "island_role text        NOT NULL)";

  public static final String INSERT_PLAYER = "REPLACE INTO spongysb_players (player_uuid, island_uuid, island_role) VALUES(?,?,?)";

  public static final String GET_PLAYER = "SELECT island_uuid, island_role FROM spongysb_players WHERE player_uuid = ?";

  public static final String GET_PLAYERS_IN_ISLAND = "SELECT (player_uuid) FROM spongysb_players WHERE island_uuid = ?";

  public static final String PLAYER_QUIT_UPDATE = "UPDATE spongysb_players SET island_uuid = ?, island_role = ? WHERE player_uuid = ?";

  public static final String CREATE_GRID_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_grid (" +
          "id         VARCHAR(1) NOT NULL PRIMARY KEY, " +
          "lastisland text       NOT NULL)";

  public static final String GET_LAST_LOCATION = "SELECT * FROM spongysb_grid";

  public static final String UPDATE_LAST_GRID_ISLAND = "REPLACE INTO spongysb_grid (id, lastisland) VALUES(1,?);";

  public static final String CREATE_ISLAND_UPGRADES_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_upgrades (" +
          "island_uuid VARCHAR(36) NOT NULL PRIMARY KEY, " +
          "island_size integer     NOT NULL)";

  public static final String CREATE_ISLAND_PERMISSIONS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_permissions (" +
          "island_uuid      VARCHAR(36) NOT NULL PRIMARY KEY, " +
          "place            text NOT NULL, " +
          "break            text NOT NULL, " +
          "entry            text NOT NULL, " +
          "spawner_place    text NOT NULL, " +
          "spawner_break    text NOT NULL, " +
          "door             text NOT NULL, " +
          "button           text NOT NULL, " +
          "lever            text NOT NULL, " +
          "gate             text NOT NULL, " +
          "container        text NOT NULL, " +
          "armorstand       text NOT NULL, " +
          "pressure_plate   text NOT NULL, " +
          "name             text NOT NULL, " +
          "view_perms       text NOT NULL, " +
          "set_perms        text NOT NULL, " +
          "invite           text NOT NULL, " +
          "kick             text NOT NULL, " +
          "warp             text NOT NULL, " +
          "view_upgrades    text NOT NULL, " +
          "set_upgrades     text NOT NULL, " +
          "promote          text NOT NULL, " +
          "demote           text NOT NULL, " +
          "ban              text NOT NULL, " +
          "home             text NOT NULL, " +
          "is_lock          text NOT NULL, " +
          "set_home         text NOT NULL, " +
          "item_drop        text NOT NULL, " +
          "item_pickup      text NOT NULL, " +
          "hurt_animals     text NOT NULL, " +
          "hurt_mobs        text NOT NULL, " +
          "spawn_eggs       text NOT NULL, " +
          "breed            text NOT NULL, " +
          "ender_pearl      text NOT NULL, " +
          "music            text NOT NULL, " +
          "animals          text NOT NULL, " +
          "send_out_pokemon text NOT NULL)";

  public static final String SAVE_ISLAND_PERMISSIONS = "REPLACE INTO spongysb_island_permissions (island_uuid, place, break, entry, spawner_place, spawner_break, door, button, lever, gate, container, armorstand, pressure_plate, name, view_perms, set_perms, invite, kick, warp, view_upgrades, set_upgrades, promote, demote, ban, home, is_lock, set_home, item_drop, item_pickup, hurt_animals, hurt_mobs, spawn_eggs, breed, ender_pearl, music, animals, send_out_pokemon) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  public static final String GET_ISLAND_PERMISSIONS_TABLE = "SELECT * FROM spongysb_island_permissions WHERE island_uuid = ?";

  public static final String CREATE_BANS_TABLE = "CREATE TABLE IF NOT EXISTS spongysb_island_bans (" +
          "player_uuid VARCHAR(36) NOT NULL," +
          "island_uuid VARCHAR(36) NOT NULL)";

  public static final String INSERT_BAN = "REPLACE INTO spongysb_island_bans (player_uuid, island_uuid) VALUES(?,?);";

  public static final String REMOVE_BAN = "DELETE FROM spongysb_island_bans WHERE player_uuid = ? AND island_uuid = ?";

  public static final String GET_BANS_OF_PLAYER = "SELECT island_uuid FROM spongysb_island_bans WHERE player_uuid = ?";




}
