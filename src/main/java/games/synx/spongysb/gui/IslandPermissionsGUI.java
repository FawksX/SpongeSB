package games.synx.spongysb.gui;

import ca.landonjw.gooeylibs.inventory.api.Button;
import ca.landonjw.gooeylibs.inventory.api.Page;
import ca.landonjw.gooeylibs.inventory.api.Template;
import com.google.common.collect.Maps;
import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.GUI;
import games.synx.spongysb.config.configs.wrappers.PermissionsGUIButton;
import games.synx.spongysb.config.configs.wrappers.SchematicGUIButton;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ClickType;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;
import java.util.Map;

public class IslandPermissionsGUI {

    public static void open(Player player) {

        SPlayer sPlayer = SPlayer.get(player);
        Island island = sPlayer.getIsland();

        GUI.GUISettings.PermissionsGUI permissionsGUI = ConfigManager.get().getGUIs().permissionsgui;

        Map<SchematicGUIButton, Button> buttons = Maps.newHashMap();
        Button fillerButton = Button.builder().item(permissionsGUI.fillerSlot.getItemStack()).displayName(permissionsGUI.fillerSlot.displayName).build();

        Template.Builder template = Template.builder(permissionsGUI.rows).fill(fillerButton);

        for(PermissionsGUIButton confButton : permissionsGUI.buttons) {

            Button button = Button.builder()
                    .item(confButton.getItemStack())
                    .displayName(confButton.displayName)
                    .lore(replaceLevel(confButton.lore, island, confButton.islandPerm))
                    .onClick((action) -> {
                        if(!sPlayer.hasPerm(IslandPerm.SET_PERMS, sPlayer.getIsland())) {
                            MessageUtil.msg(player, ConfigManager.get().getMessages().permission.can_only_view_permissions);
                            return;
                        }

                        if(action.getClickType() == ClickType.PICKUP) {
                            int newPosition = (island.getIslandPermissions().get(confButton.islandPerm).getPosition() - 1);
                            changePermission(player, island, newPosition, confButton.islandPerm);
                        }
                        if(action.getClickType() == ClickType.SWAP) {
                            int newPosition = (island.getIslandPermissions().get(confButton.islandPerm).getPosition() + 1);
                            changePermission(player, island, newPosition, confButton.islandPerm);
                        }
                        action.getPlayer().closeScreen();
                        open(player);
                    }).build();

            template.set(confButton.row, confButton.column, button);
        }

        Page.builder()
                .template(template.build())
                .title(ConfigManager.get().getGUIs().schematicgui.menuTitle)
                .build()
                .openPage((EntityPlayerMP) player);

    }

    public static void changePermission(Player player, Island island, int newPosition, IslandPerm islandPerm) {
        IslandPermissionLevel permLvL = IslandPermissionLevel.fromPosition(newPosition);
        if(permLvL == IslandPermissionLevel.NONE) {
            MessageUtil.msg(player, ConfigManager.get().getMessages().permission.cannot_demote_to_visitor);
            return;
        }
        if(permLvL == IslandPermissionLevel.LEADER) {
            MessageUtil.msg(player, ConfigManager.get().getMessages().permission.cannot_promote_to_leader);
            return;
        }
        island.getIslandPermissions().replace(islandPerm, permLvL);
    }

    public static List<String> replaceLevel(List<String> lore, Island island, IslandPerm islandPerm) {

        for(String s : lore) {
            s.replace("{level}", island.getIslandPermissions().get(islandPerm).toString());
        }
        return lore;
    }

}
