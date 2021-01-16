package games.synx.spongysb.config.configs;

import com.google.common.collect.Lists;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.wrappers.PermissionsGUIButton;
import games.synx.spongysb.config.configs.wrappers.SchematicGUIButton;
import games.synx.spongysb.objects.IslandPerm;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class GUI extends AbstractConfiguration<GUI.GUISettings> implements IConfiguration {

    public GUI(Path configFile) throws IOException {
        super(configFile, GUISettings.class);
    }

    @ConfigSerializable
    public static class GUISettings {

        @Setting
        public SchematicGUI schematicgui = new SchematicGUI();

        @Setting
        public PermissionsGUI permissionsgui = new PermissionsGUI();

        @ConfigSerializable
        public static class SchematicGUI {

            @Setting
            public String menuTitle = "§aIsland Creation";

            @Setting
            public int rows = 3;

            @Setting
            public FillerButton fillerSlot = new FillerButton();

            @Setting
            public List<SchematicGUIButton> buttons = Lists.newArrayList(new SchematicGUIButton());

        }

        @ConfigSerializable
        public static class PermissionsGUI {

            @Setting
            public String menuTitle = "§3Island Permissions";

            @Setting
            public int rows = 6;

            @Setting
            public FillerButton fillerSlot = new FillerButton();

            /**
             * SETTINGS NOT HERE:
             * VIEW_SETTINGS
             * SET_SETTINGS
             * ITEM_DROP
             * ITEM_PICKUP
             * HURT_ANIMALS
             * HURT_MOBS
             * SPAWN_EGGS
             * BREED
             * ENDER_PEARL
             * MUSIC
             * ANIMALS
             */
            @Setting
            public List<PermissionsGUIButton> buttons = Lists.newArrayList(new PermissionsGUIButton());



        }



    }

}
