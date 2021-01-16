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

            private final List<String> normalLore = Lists.newArrayList(
                    "§aThis Permission Does Something!",
                    "§a§lCURRENT PERMISSION LEVEL: §c{level}",
                    "§eLeft Click to Decrease Needed Level",
                    "§eRight Click to Increase Needed Level"
            );

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
            public List<PermissionsGUIButton> buttons = Lists.newArrayList(
                    PermissionsGUIButton.builder().row(1).column(1).item("pixelmon:tm12").name("§bPlace").islandPerm(IslandPerm.PLACE).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(1).column(2).item("pixelmon:tm12").name("§bBreak").islandPerm(IslandPerm.BREAK).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(1).column(3).item("pixelmon:tm12").name("§bSpawner Break").islandPerm(IslandPerm.SPAWNER_BREAK).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(1).column(4).item("pixelmon:tm12").name("§bSpawner Place").islandPerm(IslandPerm.SPAWNER_PLACE).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(1).column(5).item("pixelmon:tm12").name("§bDoor").islandPerm(IslandPerm.DOOR).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(1).column(6).item("pixelmon:tm12").name("§bButton").islandPerm(IslandPerm.BUTTON).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(1).column(7).item("pixelmon:tm12").name("§bLever").islandPerm(IslandPerm.LEVER).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(2).column(1).item("pixelmon:tm12").name("§bGate").islandPerm(IslandPerm.GATE).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(2).column(2).item("pixelmon:tm12").name("§bContainer").islandPerm(IslandPerm.CONTAINER).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(2).column(3).item("pixelmon:tm12").name("§bArmorstand").islandPerm(IslandPerm.ARMORSTAND).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(2).column(4).item("pixelmon:tm12").name("§bPressurePlate").islandPerm(IslandPerm.PRESSURE_PLATE).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(2).column(5).item("pixelmon:tm12").name("§bName").islandPerm(IslandPerm.NAME).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(2).column(6).item("pixelmon:tm12").name("§bDescription").islandPerm(IslandPerm.DESCRIPTION).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(2).column(7).item("pixelmon:tm12").name("§bView Perms").islandPerm(IslandPerm.VIEW_PERMS).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(3).column(1).item("pixelmon:tm12").name("§bSet Perms").islandPerm(IslandPerm.SET_PERMS).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(3).column(2).item("pixelmon:tm12").name("§bInvite").islandPerm(IslandPerm.INVITE).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(3).column(3).item("pixelmon:tm12").name("§bKick").islandPerm(IslandPerm.KICK).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(3).column(4).item("pixelmon:tm12").name("§bDeposit").islandPerm(IslandPerm.DEPOSIT).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(3).column(5).item("pixelmon:tm12").name("§bWithdraw").islandPerm(IslandPerm.WITHDRAW).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(3).column(6).item("pixelmon:tm12").name("§bWarp").islandPerm(IslandPerm.WARP).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(3).column(7).item("pixelmon:tm12").name("§bView Upgrades").islandPerm(IslandPerm.VIEW_UPGRADES).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(4).column(1).item("pixelmon:tm12").name("§bSet Upgrades").islandPerm(IslandPerm.SET_UPGRADES).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(4).column(2).item("pixelmon:tm12").name("§bPromote").islandPerm(IslandPerm.PROMOTE).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(4).column(3).item("pixelmon:tm12").name("§bDemote").islandPerm(IslandPerm.DEMOTE).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(4).column(4).item("pixelmon:tm12").name("§bBan").islandPerm(IslandPerm.BAN).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(4).column(5).item("pixelmon:tm12").name("§bHome").islandPerm(IslandPerm.HOME).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(4).column(6).item("pixelmon:tm12").name("§bLock").islandPerm(IslandPerm.IS_LOCK).lore(normalLore).build(),
                    PermissionsGUIButton.builder().row(4).column(7).item("pixelmon:tm12").name("§bSet Home").islandPerm(IslandPerm.SET_HOME).lore(normalLore).build()
            );



        }



    }

}
