package games.synx.spongysb.config.configs.guis;

import com.google.common.collect.Lists;
import games.synx.pscore.config.annotation.Config;
import games.synx.pscore.config.gui.templates.FillerButton;
import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.config.configs.guis.button.PermissionsGUIButton;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@Config(
        file = "permissions.json",
        clazz = PermGUI.PermGUISettings.class
)
public class PermGUI extends AbstractConfiguration implements IConfiguration {

    @ConfigSerializable
    public static class PermGUISettings implements IGUIConfig<PermissionsGUIButton> {

        @Setting
        private String menuTitle = "§3Island Permissions";

        @Setting
        private int rows = 6;

        @Setting
        private FillerButton fillerSlot = new FillerButton();

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
        private List<PermissionsGUIButton> buttons = Lists.newArrayList(new PermissionsGUIButton());


        @Override
        public String getMenuTitle() {
            return this.menuTitle;
        }

        @Override
        public int getRows() {
            return this.rows;
        }

        @Override
        public FillerButton getFillerItem() {
            return this.fillerSlot;
        }

        @Override
        public List<PermissionsGUIButton> getButtons() {
            return this.buttons;
        }

    }

}
