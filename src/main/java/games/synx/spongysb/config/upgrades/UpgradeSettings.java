package games.synx.spongysb.config.upgrades;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ConfigSerializable
public class UpgradeSettings {

  @Setting
  public Map<Integer, Integer> size_upgrades = Stream.of(new int[][] {
      {1, 100},
      {2, 200}
  }).collect(Collectors.toMap(data -> data[0], data -> data[1]));



}
