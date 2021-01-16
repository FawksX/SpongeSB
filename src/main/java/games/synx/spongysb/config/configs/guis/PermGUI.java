package games.synx.spongysb.config.configs.guis;

import com.google.common.collect.Lists;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.wrappers.PermissionsGUIButton;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class PermGUI extends AbstractConfiguration<PermGUI.PermGUISettings> implements IConfiguration {

    public PermGUI(Path configFile) throws IOException {
        super(configFile, PermGUISettings.class);
    }

    @ConfigSerializable
    public static class PermGUISettings {

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
