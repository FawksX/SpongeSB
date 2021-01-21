package games.synx.spongysb.config.configs;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.wrappers.IslandUpgradeWrapper;
import games.synx.spongysb.objects.enums.UpgradeType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Upgrades extends AbstractConfiguration<Upgrades.UpgradeSettings> implements IConfiguration {

  public Upgrades(Path configFile) throws IOException {
    super(configFile, UpgradeSettings.class);
  }

  @ConfigSerializable
  public static class UpgradeSettings {

    @Setting
    public int rows = 3;

    @Setting
    public String menuTitle = "§d§lIsland Upgrades";

    @Setting
    public FillerButton fillerSlot = new FillerButton();


    @Setting
    public List<UpgradeButton> buttons = Lists.newArrayList(new UpgradeButton());


    @ConfigSerializable
    public static class UpgradeButton {

      @Setting
      public UpgradeType upgradeType = UpgradeType.SIZE;

      @Setting
      public int column = 4;

      @Setting
      public int row = 1;

      @Setting
      public String item = "minecraft:glowstone";

      @Setting
      public String displayName = "§dItemName!";

      public ItemStack getItemStack() {
        return new ItemStack(Item.getByNameOrId(item));
      }

      @Setting
      public Map<String, IslandUpgradeWrapper> tiers = new HashMap<String, IslandUpgradeWrapper>() {{
        put("0", new IslandUpgradeWrapper());
        put("1", new IslandUpgradeWrapper());
      }};

    }

    @Setting
    public Map<String, Map<String, Double>> oreGeneratorValues = Maps.newHashMap();


  }

}