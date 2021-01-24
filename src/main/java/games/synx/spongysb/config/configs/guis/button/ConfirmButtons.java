package games.synx.spongysb.config.configs.guis.button;

import ca.landonjw.gooeylibs.inventory.api.Button;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ConfirmButtons {

    public static final Button.Builder CONFIRM_BUTTON =
            Button.builder()
                    .item(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 5))
                    .displayName("§a§lCONFIRM");

    public static final Button DENY_BUTTON =
            Button.builder()
                    .item(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 14))
                    .displayName("§c§lCANCEL")
                    .onClick((action) -> action.getPlayer().closeScreen())
                    .build();

}
