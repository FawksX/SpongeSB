package games.synx.spongysb.config.configs;

import ca.landonjw.gooeylibs.inventory.api.Button;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.guis.button.IGUIButton;
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
    public static class UpgradeButton implements IGUIButton {

      @Setting
      private final UpgradeType UPGRADE_TYPE = UpgradeType.SIZE;

      @Setting
      private final int COLUMN = 4;

      @Setting
      private final int ROW = 1;

      @Setting
      private final String ITEM = "minecraft:glowstone";

      @Setting
      private final String DISPLAY_NAME = "§dItemName!";

      @Setting
      private final Map<String, IslandUpgradeWrapper> TIERS = new HashMap<String, IslandUpgradeWrapper>() {{
        put("0", new IslandUpgradeWrapper());
        put("1", new IslandUpgradeWrapper());
      }};


      @Override
      public int getColumn() {
        return this.COLUMN;
      }

      @Override
      public int getRow() {
        return this.ROW;
      }

      public ItemStack getItemStack() {
        return new ItemStack(Item.getByNameOrId(this.ITEM));
      }

      @Override
      public String getDisplayName() {
        return this.DISPLAY_NAME;
      }

      @Override
      public List<String> getLore() {
        return null;
      }

      @Override
      public Button.Builder getButtonBuilder() {
        return null;
      }

      public UpgradeType getUpgradeType() {
        return this.UPGRADE_TYPE;
      }

      public Map<String, IslandUpgradeWrapper> getTiers() {
        return this.TIERS;
      }

    }

    @Setting
    public Map<String, Map<String, Double>> oreGeneratorValues = Maps.newHashMap();


  }

}