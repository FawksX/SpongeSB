package games.synx.spongysb.config.upgrades;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

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
