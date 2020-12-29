package games.synx.spongysb.config.messages;

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
  public HelpCommand helpCommand = new HelpCommand();

  @ConfigSerializable
  public static class HelpCommand {

    @Setting
    public String header = "&8&m--------------------&e&lHELP&8&m--------------------";

    @Setting
    public List<String> body = Arrays.asList(
        "&7- &6/is new <island name> &7- &eCreate a new Island!",
        "&7- &6/is go &7- &eTeleport to your Island"
        );

  }


}
