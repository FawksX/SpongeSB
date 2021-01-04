package games.synx.spongysb.objects;

public enum IslandPermissionLevel {

  NONE(""),
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

}
