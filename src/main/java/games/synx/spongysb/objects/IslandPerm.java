package games.synx.spongysb.objects;

/**
 * IslandPerm Enum for creating a designated place for permissions.
 * THESE ARE NOT CURRENTLY IMPLEMENTED AS THERE IS NO PERMISSION SYSTEM! Just invite/deinvite
 * Temporary comments throughout this enum to mark which ones are completed (in terms of logic)
 */
public enum IslandPerm {
  PLACE("place"), // cmpl
  BREAK("break"), // cmpl
  ENTRY("entry"), // cmpl, /is lock

  SPAWNER_PLACE("spawner_place"),
  SPAWNER_BREAK("spawner_break"),
  DOOR("door"),
  BUTTON("button"),
  LEVER("lever"),
  GATE("gate"),
  CONTAINER("container"),
  ARMORSTAND("armorstand"),
  PRESSURE_PLATE("pressure_plate"),

  NAME("name"), // cmpl
  VIEW_PERMS("view_perms"), //cmpl
  SET_PERMS("set_perms"), //cmpl
  INVITE("invite"), // cmpl
  KICK("kick"), // cmpl
  DEPOSIT("deposit"),
  WITHDRAW("withdraw"),
  WARP("warp"),
  VIEW_UPGRADES("view_upgrades"),
  SET_UPGRADES("set_upgrades"),
  PROMOTE("promote"), //cmpl
  DEMOTE("demote"), //cmpl
  BAN("ban"),
  HOME("home"), // cmpl
  IS_LOCK("is_lock"), //cmpl
  SET_HOME("set_home"), //cmpl

  ITEM_DROP("item_drop"), //cmpl
  ITEM_PICKUP("item_pickup"), //cmpl
  HURT_ANIMALS("hurt_animals"),
  HURT_MOBS("hurt_mobs"),
  SPAWN_EGGS("spawn_eggs"),
  BREED("breed"),
  ENDER_PEARL("ender_pearl"),
  MUSIC("music"),
  ANIMALS("animals"),

  // PIXELMON
  SEND_OUT_POKEMON("send_out_pokemon"); //cmpl

  private final String permType;

  private IslandPerm(String permType) {
    this.permType = permType;
  }

  @Override
  public String toString() {
    return this.permType;
  }

  public static IslandPerm fromString(String type) {
    for(IslandPerm pl : IslandPerm.values()) {
      if(pl.toString().equals(type)) {
        return pl;
      }
    }
    return null;
  }

}
