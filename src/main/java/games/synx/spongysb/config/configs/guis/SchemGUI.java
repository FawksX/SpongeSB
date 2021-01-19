package games.synx.spongysb.config.configs.guis;

import com.google.common.collect.Lists;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.guis.button.SchematicGUIButton;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SchemGUI extends AbstractConfiguration<SchemGUI.SchemGUISettings> implements IConfiguration {

    public SchemGUI(Path configFile) throws IOException {
        super(configFile, SchemGUISettings.class);
    }

    @ConfigSerializable
    public static class SchemGUISettings {

        @Setting
        public String menuTitle = "§aIsland Creation";

        @Setting
        public int rows = 3;

        @Setting
        public FillerButton fillerSlot = new FillerButton();

        @Setting
        public List<SchematicGUIButton> buttons = Lists.newArrayList(new SchematicGUIButton());

    }
}
