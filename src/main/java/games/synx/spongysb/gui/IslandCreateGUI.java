package games.synx.spongysb.gui;

import ca.landonjw.gooeylibs.inventory.api.Button;
import ca.landonjw.gooeylibs.inventory.api.Page;
import ca.landonjw.gooeylibs.inventory.api.Template;
import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.guis.IGUIConfig;
import games.synx.spongysb.config.configs.guis.SchemGUI;
import games.synx.spongysb.config.configs.guis.button.SchematicGUIButton;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.generation.SchematicManager;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.entity.living.player.Player;

public class IslandCreateGUI {

    public static void open(Player player, String name) {

        final IGUIConfig<SchematicGUIButton> GUI_SETTINGS = ConfigManager.get().getSchematicGUI();

        Template.Builder template = Template.builder(GUI_SETTINGS.getRows()).fill(GUI_SETTINGS.getFillerItem().getFillerButton());

        for(SchematicGUIButton confButton : GUI_SETTINGS.getButtons()) {

            Button button = confButton.getButtonBuilder()
                    .onClick((action) -> {
                        MessageUtil.msg(player, ConfigManager.get().getMessages().creation.creating_island);
                        action.getPlayer().closeScreen();
                        GridManager.get().newIsland(player, SchematicManager.get().getSchematicHandlers().get(confButton.getSchematic()), name);
                        MessageUtil.msg(player, ConfigManager.get().getMessages().creation.island_created_successfully);
                    }).build();

            template.set(confButton.getRow(), confButton.getColumn(), button);
        }

        Page.builder()
                .template(template.build())
                .title(GUI_SETTINGS.getMenuTitle())
                .build()
                .openPage((EntityPlayerMP) player);

    }


}
