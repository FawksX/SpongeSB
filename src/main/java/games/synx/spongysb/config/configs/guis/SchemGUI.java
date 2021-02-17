package games.synx.spongysb.config.configs.guis;

import com.google.common.collect.Lists;
import games.synx.pscore.config.annotation.Config;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.guis.button.SchematicGUIButton;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Config(
        file = "createisland.json",
        clazz = SchemGUI.SchemGUISettings.class
)
public class SchemGUI extends AbstractConfiguration implements IConfiguration {

    @ConfigSerializable
    public static class SchemGUISettings implements IGUIConfig<SchematicGUIButton> {

        @Setting
        private final String menuTitle = "§aIsland Creation";

        @Setting
        private final int rows = 3;

        @Setting
        private final  FillerButton fillerSlot = new FillerButton();

        @Setting
        private final List<SchematicGUIButton> buttons = Lists.newArrayList(new SchematicGUIButton());

        @Override
        public String getMenuTitle() {
            return this.menuTitle;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public FillerButton getFillerItem() {
            return fillerSlot;
        }

        @Override
        public List<SchematicGUIButton> getButtons() {
            return buttons;
        }
    }
}
