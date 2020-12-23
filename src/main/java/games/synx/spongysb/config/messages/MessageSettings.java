package games.synx.spongysb.config.messages;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MessageSettings {

  @Setting
  public String serverName = "Lightning";


}
