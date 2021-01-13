package games.synx.spongysb.objects;

public enum IslandPermissionLevel {

  NONE("visitor", 0),
  COOP("coop", 1),
  MEMBER("member", 2),
  MOD("mod", 3),
  ADMIN("admin", 4),
  LEADER("leader", 5);

  private final String permissionLevel;
  private final int position;

  private IslandPermissionLevel(String permissionLevel, int position) {
    this.permissionLevel = permissionLevel;
    this.position = position;
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

  public int getPosition() {
    return this.position;
  }

  public static IslandPermissionLevel fromPosition(int position) {
    for(IslandPermissionLevel pl : IslandPermissionLevel.values()) {
      if(pl.getPosition() == position) {
        return pl;
      }
    }
    return null;
  }

}
