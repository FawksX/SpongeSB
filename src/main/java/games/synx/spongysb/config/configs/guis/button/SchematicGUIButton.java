package games.synx.spongysb.config.configs.guis.button;

import ca.landonjw.gooeylibs.inventory.api.Button;
import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class SchematicGUIButton implements IGUIButton {

    @Setting
    private final int COLUMN = 4;

    @Setting
    private final int ROW = 1;

    @Setting
    private final String ITEM = "minecraft:glowstone";

    @Setting
    private final String DISPLAY_NAME = "ItemName!";

    @Setting
    private final List<String> LORE = Lists.newArrayList();

    @Setting
    private final String SCHEMATIC = "default.schematic";

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
        return this.LORE;
    }

    public String getSchematic() {
        return this.SCHEMATIC;
    }

    public Button.Builder getButtonBuilder() {
        return Button.builder().item(getItemStack()).displayName(this.DISPLAY_NAME).lore(this.LORE);
    }

}
