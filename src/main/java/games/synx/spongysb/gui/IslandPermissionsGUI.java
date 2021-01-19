package games.synx.spongysb.gui;

import ca.landonjw.gooeylibs.inventory.api.Button;
import ca.landonjw.gooeylibs.inventory.api.Page;
import ca.landonjw.gooeylibs.inventory.api.Template;
import com.google.common.collect.Lists;
import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Messages;
import games.synx.spongysb.config.configs.guis.PermGUI;
import games.synx.spongysb.config.configs.wrappers.PermissionsGUIButton;
import games.synx.spongysb.objects.Island;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.enums.IslandPermissionLevel;
import games.synx.spongysb.objects.SPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ClickType;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;

public class IslandPermissionsGUI {

    private static final Messages.MessageSettings messages = ConfigManager.get().getMessages();

    public static void open(Player player) {

        SPlayer sPlayer = SPlayer.get(player);
        Island island = sPlayer.getIsland();

        PermGUI.PermGUISettings permissionsGUI = ConfigManager.get().getPermissionsGUI();

        Template.Builder template = Template.builder(permissionsGUI.rows).fill(permissionsGUI.fillerSlot.getFillerButton());

        for(PermissionsGUIButton confButton : permissionsGUI.buttons) {

            Button button = Button.builder()
                    .item(confButton.getItemStack())
                    .displayName(confButton.displayName)
                    .lore(replaceLevel(confButton.lore, island, confButton.islandPerm))
                    .onClick((action) -> {
                        if(!sPlayer.hasPerm(IslandPerm.SET_PERMS, sPlayer.getIsland())) {
                            MessageUtil.msg(player, messages.permission.can_only_view_permissions);
                            return;
                        }

                        int position = island.getIslandPermissions().get(confButton.islandPerm).getPosition();

                        if(action.getClickType() == ClickType.QUICK_MOVE) {
                            position = position - 1;
                        }
                        if(action.getClickType() == ClickType.PICKUP) {
                            position = position + 1;
                        }

                        changePermission(player, island, position, confButton.islandPerm);
                        action.getButton().toBuilder().lore(replaceLevel(confButton.lore, island, confButton.islandPerm)).build();

                    }).build();

            template.set(confButton.row, confButton.column, button);
        }

        Page.builder()
                .template(template.build())
                .title(permissionsGUI.menuTitle)
                .build()
                .openPage((EntityPlayerMP) player);

    }

    private static void changePermission(Player player, Island island, int newPosition, IslandPerm islandPerm) {

        if(newPosition < IslandPermissionLevel.NONE.getPosition()) {
            MessageUtil.msg(player, messages.permission.cannot_put_less_than_visitor);
            return;
        }
        if(newPosition > IslandPermissionLevel.LEADER.getPosition()) {
            MessageUtil.msg(player, messages.permission.cannot_put_more_than_leader);
            return;
        }

        island.getIslandPermissions().replace(islandPerm, IslandPermissionLevel.fromPosition(newPosition));
    }

    private static List<String> replaceLevel(List<String> lore, Island island, IslandPerm islandPerm) {

        List<String> newLore = Lists.newArrayList();
        for(String s : lore) {
            newLore.add(s.replace("{level}", island.getIslandPermissions().get(islandPerm).toString()));
        }
        return newLore;
    }

}
