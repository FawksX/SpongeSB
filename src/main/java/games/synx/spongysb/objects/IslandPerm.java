package games.synx.spongysb.objects;

/**
 * IslandPerm Enum for creating a designated place for permissions.
 * THESE ARE NOT CURRENTLY IMPLEMENTED AS THERE IS NO PERMISSION SYSTEM! Just invite/deinvite
 */
public enum IslandPerm {
  PLACE("place"),
  BREAK("break"),
  ENTRY("entry"),
  SPAWNER_PLACE("spawner_place"),
  SPAWNER_BREAK("spawner_break"),
  DOOR("door"),
  BUTTON("button"),
  LEVER("lever"),
  GATE("gate"),
  CONTAINER("container"),
  ARMORSTAND("armorstand"),
  PRESSURE_PLATE("pressure_plate"),

  NAME("name"),
  DESCRIPTION("description"),
  VIEW_PERMS("view_perms"),
  SET_PERMS("set_perms"),
  VIEW_SETTINGS("view_settings"),
  SET_SETTINGS("set_settings"),
  INVITE("invite"),
  KICK("kick"),
  DEPOSIT("deposit"),
  WITHDRAW("withdraw"),
  WARP("warp"),
  VIEW_UPGRADES("view_upgrades"),
  SET_UPGRADES("set_upgrades"),
  PROMOTE("promote"),
  DEMOTE("demote"),
  BAN("ban"),
  HOME("home"),
  IS_LOCK("is_lock"),
  SET_HOME("set_home"),

  ITEM_DROP("item_drop"),
  ITEM_PICKUP("item_pickup"),
  HURT_ANIMALS("hurt_animals"),
  HURT_MOBS("hurt_mobs"),
  SPAWN_EGGS("spawn_eggs"),
  BREED("breed"),
  ENDER_PEARL("ender_pearl"),
  MUSIC("music"),
  ANIMALS("animals"),

  // PIXELMON
  SEND_OUT_POKEMON("send_out_pokemon");

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
