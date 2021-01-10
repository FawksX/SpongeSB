package games.synx.spongysb.objects;

public enum IslandPermissionLevel {

  NONE("visitor"),
  COOP("coop"),
  MEMBER("member"),
  MOD("mod"),
  ADMIN("admin"),
  LEADER("leader");

  private final String permissionLevel;

  private IslandPermissionLevel(String permissionLevel) {
    this.permissionLevel = permissionLevel;
  }

  @Override
  public String toString() {
    return this.permissionLevel;
  }

  public static IslandPermissionLevel fromString(String type) {
    for(IslandPermissionLevel pl : IslandPermissionLevel.values()) {
      if(pl.toString().equals(type)) {
        return pl;
      }
    }
    return null;
  }

}
