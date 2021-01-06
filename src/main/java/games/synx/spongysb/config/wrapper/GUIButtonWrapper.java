package games.synx.spongysb.config.wrapper;

import net.minecraft.item.ItemStack;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class GUIButtonWrapper {

    private int row;
    private int column;
    private ItemStack item;
    private String displayName;
    private List<String> lore;

    public GUIButtonWrapper(
            int row,
            int column,
           // ItemStack item,
            String displayName,
            List<String> lore
    ) {
        this.row = row;
        this.column = column;
     //   this.item = item;
        this.displayName = displayName;
        this.lore = lore;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public List<String> getLore() {
        return this.lore;
    }

}
