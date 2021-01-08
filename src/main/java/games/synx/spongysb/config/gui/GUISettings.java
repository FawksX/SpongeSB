package games.synx.spongysb.config.gui;

import com.google.common.collect.Lists;
import games.synx.pscore.config.gui.templates.FillerButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class GUISettings {

    @Setting
    public SchematicGUI schematicgui = new SchematicGUI();

    @ConfigSerializable
    public static class SchematicGUI {

        @Setting
        public String menuTitle = "§aIsland Creation";

        @Setting
        public int rows = 3;

        @Setting
        public FillerButton fillerSlot = new FillerButton();

        @Setting
        public List<GUIButton> buttons = Lists.newArrayList(new GUIButton());

    }

    @ConfigSerializable
    public static class GUIButton {

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


}
