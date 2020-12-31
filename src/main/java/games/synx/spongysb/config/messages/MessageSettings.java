package games.synx.spongysb.config.messages;

import co.aikar.commands.annotation.HelpCommand;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.Arrays;
import java.util.List;

@ConfigSerializable
public class MessageSettings {

  @Setting
  public String serverName = "Lightning";

  @Setting
  public String is_in_island_error = "&cYou are already in an island!";

  @Setting
  public String island_created_successfully = "&aYour Island has been created successfully!";

  @Setting
  public String player_not_in_island = "&cYou are not in an island! Type /is new <island name> to create an island!";

  @Setting
  public String teleporting_to_your_island = "&aTeleporting to your island...";

  @Setting
  public String teleporting_to_default_home_location = "&aCould not teleport to your Island, Teleporting you to default home location!";

  @Setting
  public String could_not_teleport_to_island = "&cCould not teleport to your island. Contact Staff for help!";

  @Setting
  public String enabled_admin_bypass = "&e&lISLAND &aYou have successfully &lENABLED&a Island Bypass!";

  @Setting
  public String disable_admin_bypass = "&e&lISLAND &cYou have successfully &lDISABLED&c Island Bypass!";

  @Setting
  public String only_allowed_to_teleport_to_own_island = "&e&lISLAND &cYou can only teleport to your own island!";

  @Setting
  public String player_not_online = "&e&lISLAND &cThe specified player is not online!";

  @Setting
  public String invite_revoked_successfully = "&e&lISLAND  &ePlayer &a%s &ehas had their invite &crevoked successfully!";

  @Setting
  public String invited_player_successfully = "&e&lISLAND  &ePlayer &a%s &ehas been &ainvited successfully!";

  @Setting
  public String invited_to_island = "&e&lISLAND &a%s &ehas invited you to join &a%s&e. Type &a/is join %s &eto join!";

  @Setting
  public String must_leave_current_island = "&e&lISLAND &cYou must leave your current island to join another!";

  @Setting
  public String joined_island_successfully = "&e&lISLAND &eYou have &asuccessfully &ejoined &a%s!";

  @Setting
  public String could_not_join_island = "&e&lISLAND &eYou do not have a pending invite to &c%s!";

  @Setting
  public String island_does_not_exist = "&e&lISLAND &eIsland &c%s &edoes not exist!";

}
