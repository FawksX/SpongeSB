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
    private int column = 1;

    @Setting
    private int row = 1;

    @Setting
    private IslandPerm islandPerm = IslandPerm.PLACE;

    @Setting
    private String item = "pixelmon:tm12";

    @Setting
    private String displayName = "§c§lPLACE";

    @Setting
    private final List<String> lore = Lists.newArrayList(
            "§eCurrent Permission Level: §a§l{level}",
            "§d§lLeft Click to increase needed level",
            "§e§lShift Click to decrease needed level"
    );

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
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

    @Override
    public Button.Builder getButtonBuilder() {
        return Button.builder().item(getItemStack()).displayName(this.displayName);
    }

    public IslandPerm getIslandPerm() {
        return this.islandPerm;
    }


}
