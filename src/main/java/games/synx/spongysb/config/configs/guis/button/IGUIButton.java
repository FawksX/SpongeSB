package games.synx.spongysb.config.configs.guis.button;

import ca.landonjw.gooeylibs.inventory.api.Button;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IGUIButton {

    int getColumn();

    int getRow();

    ItemStack getItemStack();

    String getDisplayName();

    List<String> getLore();

    Button.Builder getButtonBuilder();



}
