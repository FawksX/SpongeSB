package games.synx.spongysb.config.configs.guis;

import com.google.common.collect.Lists;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.guis.button.PermissionsGUIButton;
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
    public static class PermGUISettings implements IGUIConfig<PermissionsGUIButton> {

        @Setting
        private final String MENU_TITLE = "§3Island Permissions";

        @Setting
        private final int ROWS = 6;

        @Setting
        private final FillerButton FILLER_SLOT = new FillerButton();

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
        private final List<PermissionsGUIButton> BUTTONS = Lists.newArrayList(new PermissionsGUIButton());


        @Override
        public String getMenuTitle() {
            return this.MENU_TITLE;
        }

        @Override
        public int getRows() {
            return this.ROWS;
        }

        @Override
        public FillerButton getFillerItem() {
            return this.FILLER_SLOT;
        }

        @Override
        public List<PermissionsGUIButton> getButtons() {
            return this.BUTTONS;
        }

    }

}
