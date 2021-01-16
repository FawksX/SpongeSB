package games.synx.spongysb.config.configs.wrappers;

import com.google.common.collect.Lists;
import games.synx.spongysb.objects.IslandPerm;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class PermissionsGUIButton {

    protected PermissionsGUIButton(int column, int row, IslandPerm islandPerm, String item, String displayName, List<String> lore) {
        this.column = column;
        this.row = row;
        this.islandPerm = islandPerm;
        this.item = item;
        this.displayName = displayName;
        this.lore = lore;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Setting
    public int column = 4;

    @Setting
    public int row = 1;

    @Setting
    public IslandPerm islandPerm = IslandPerm.NAME;

    @Setting
    public String item = "pixelmon:tm12";

    @Setting
    public String displayName = "ItemName";

    @Setting
    public List<String> lore = Lists.newArrayList();

    public ItemStack getItemStack() {
        return new ItemStack(Item.getByNameOrId(item));
    }


    public static class Builder {

        private int column;
        private int row;
        private IslandPerm islandPerm;
        private String item;
        private String displayName;
        private List<String> lore;

        public Builder column(int column) {
            this.column = column;
            return this;
        }

        public Builder row(int row) {
            this.row = row;
            return this;
        }

        public Builder islandPerm(IslandPerm islandPerm) {
            this.islandPerm = islandPerm;
            return this;
        }

        public Builder item(String item) {
            this.item = item;
            return this;
        }

        public Builder name(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder lore(List<String> lore) {
            this.lore = lore;
            return this;
        }

        public PermissionsGUIButton build() {
            return new PermissionsGUIButton(
                    this.column,
                    this.row,
                    this.islandPerm,
                    this.item,
                    this.displayName,
                    this.lore
            );
        }

    }

}
