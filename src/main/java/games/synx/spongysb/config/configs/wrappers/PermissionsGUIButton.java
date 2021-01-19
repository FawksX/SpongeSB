package games.synx.spongysb.config.configs.wrappers;

import com.google.common.collect.Lists;
import games.synx.spongysb.objects.enums.IslandPerm;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class PermissionsGUIButton {

    public PermissionsGUIButton() {

    }

    @Setting
    public int column = 1;

    @Setting
    public int row = 1;

    @Setting
    public IslandPerm islandPerm = IslandPerm.PLACE;

    @Setting
    public String item = "pixelmon:tm12";

    @Setting
    public String displayName = "§c§lPLACE";

    @Setting
    public List<String> lore = Lists.newArrayList(
            "§eCurrent Permission Level: §a§l{level}",
            "§d§lLeft Click to increase needed level",
            "§e§lShift Click to decrease needed level"
    );

    public ItemStack getItemStack() {
        return new ItemStack(Item.getByNameOrId(item));
    }


}
