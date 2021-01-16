package games.synx.spongysb.config.configs.wrappers;

import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class SchematicGUIButton {

    @Setting
    public int column = 4;

    @Setting
    public int row = 1;

    @Setting
    public String item = "minecraft:glowstone";

    @Setting
    public String displayName = "ItemName!";

    @Setting
    public List<String> lore = Lists.newArrayList();

    @Setting
    public String schematic = "default.schematic";

    public ItemStack getItemStack() {
        return new ItemStack(Item.getByNameOrId(item));
    }

}
