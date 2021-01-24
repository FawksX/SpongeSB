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
    private int column = 4;

    @Setting
    private int row = 1;

    @Setting
    private String item = "minecraft:glowstone";

    @Setting
    private String displayName = "ItemName!";

    @Setting
    private List<String> lore = Lists.newArrayList();

    @Setting
    private String schematic = "default.schematic";

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    public ItemStack getItemStack() {
        return new ItemStack(Item.getByNameOrId(this.item));
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public List<String> getLore() {
        return this.lore;
    }

    public String getSchematic() {
        return this.schematic;
    }

    public Button.Builder getButtonBuilder() {
        return Button.builder().item(getItemStack()).displayName(this.displayName).lore(this.lore);
    }

}
