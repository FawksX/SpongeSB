package games.synx.spongysb.gui;

import ca.landonjw.gooeylibs.inventory.api.Button;
import ca.landonjw.gooeylibs.inventory.api.Page;
import ca.landonjw.gooeylibs.inventory.api.Template;
import com.google.common.collect.Maps;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.gui.GUISettings;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.generation.SchematicManager;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Map;

public class IslandCreateGUI {

    public static void open(Player player, String name) {

        GUISettings.SchematicGUI guiSettings = ConfigManager.get().getGUIs().schematicgui;

        Map<GUISettings.GUIButton, Button> buttons = Maps.newHashMap();
        Button fillerButton = Button.builder().item(guiSettings.fillerSlot.getItemStack()).displayName(guiSettings.fillerSlot.displayName).build();

        Template.Builder template = Template.builder(guiSettings.rows).fill(fillerButton);

        for(GUISettings.GUIButton confButton : guiSettings.buttons) {

            Button button = Button.builder()
                    .item(confButton.getItemStack())
                    .displayName(confButton.displayName)
                    .lore(confButton.lore)
                    .onClick((action) -> {
                        GridManager.get().newIsland(player, SchematicManager.get().getSchematicHandlers().get(confButton.schematic), name);
                        player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(ConfigManager.get().getMessages().island_created_successfully));
                        action.getPlayer().closeScreen();
                    }).build();

            template.set(confButton.row, confButton.column, button);

        }

        // Temporary Hard-code, to be made configurable.
        Page.builder()
                .template(template.build())
                .title(ConfigManager.get().getGUIs().schematicgui.menuTitle)
                .build()
                .openPage(FMLServerHandler.instance().getServer().getPlayerList().getPlayerByUUID(player.getUniqueId()));

    }


}
