package games.synx.spongysb.gui;

import ca.landonjw.gooeylibs.inventory.api.ButtonAction;
import ca.landonjw.gooeylibs.inventory.api.Page;
import ca.landonjw.gooeylibs.inventory.api.Template;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.spongysb.config.configs.guis.button.ConfirmButtons;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.entity.living.player.Player;

import java.util.function.Consumer;

public class CommandConfirmGUI {

    public static void open(Player player, Consumer<ButtonAction> actions) {

        Template.Builder template = Template.builder(3).fill(new FillerButton().getFillerButton());
        template.set(1, 2, ConfirmButtons.CONFIRM_BUTTON.onClick(actions).build());
        template.set(1, 6, ConfirmButtons.DENY_BUTTON);
        Page.builder().template(template.build()).title("§aConfirmation").build().forceOpenPage((EntityPlayerMP) player);
    }

}
