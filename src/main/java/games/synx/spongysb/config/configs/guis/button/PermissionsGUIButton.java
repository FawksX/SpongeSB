package games.synx.spongysb.config.configs.guis.button;

import ca.landonjw.gooeylibs.inventory.api.Button;
import com.google.common.collect.Lists;
import games.synx.spongysb.objects.enums.IslandPerm;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class PermissionsGUIButton implements IGUIButton {

    @Setting
    private final int COLUMN = 1;

    @Setting
    private final int ROW = 1;

    @Setting
    private final IslandPerm ISLAND_PERM = IslandPerm.PLACE;

    @Setting
    private final String ITEM = "pixelmon:tm12";

    @Setting
    private final String DISPLAY_NAME = "§c§lPLACE";

    @Setting
    private final List<String> LORE = Lists.newArrayList(
            "§eCurrent Permission Level: §a§l{level}",
            "§d§lLeft Click to increase needed level",
            "§e§lShift Click to decrease needed level"
    );

    @Override
    public int getColumn() {
        return this.COLUMN;
    }

    @Override
    public int getRow() {
        return this.ROW;
    }

    @Override
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

    @Override
    public Button.Builder getButtonBuilder() {
        return Button.builder().item(getItemStack()).displayName(this.DISPLAY_NAME);
    }

    public IslandPerm getIslandPerm() {
        return this.ISLAND_PERM;
    }


}
