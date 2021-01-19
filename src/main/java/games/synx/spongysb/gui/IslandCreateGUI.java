package games.synx.spongysb.gui;

import ca.landonjw.gooeylibs.inventory.api.Button;
import ca.landonjw.gooeylibs.inventory.api.Page;
import ca.landonjw.gooeylibs.inventory.api.Template;
import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.guis.SchemGUI;
import games.synx.spongysb.config.configs.guis.button.SchematicGUIButton;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.generation.SchematicManager;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.entity.living.player.Player;

public class IslandCreateGUI {

    public static void open(Player player, String name) {

        SchemGUI.SchemGUISettings guiSettings = ConfigManager.get().getSchematicGUI();

        Template.Builder template = Template.builder(guiSettings.rows).fill(guiSettings.fillerSlot.getFillerButton());

        for(SchematicGUIButton confButton : guiSettings.buttons) {

            Button button = confButton.getButtonBuilder()
                    .onClick((action) -> {
                        MessageUtil.msg(player, ConfigManager.get().getMessages().creating_island);
                        action.getPlayer().closeScreen();
                        GridManager.get().newIsland(player, SchematicManager.get().getSchematicHandlers().get(confButton.schematic), name);
                        MessageUtil.msg(player, ConfigManager.get().getMessages().island_created_successfully);
                    }).build();

            template.set(confButton.row, confButton.column, button);
        }

        Page.builder()
                .template(template.build())
                .title(guiSettings.menuTitle)
                .build()
                .openPage((EntityPlayerMP) player);

    }


}
