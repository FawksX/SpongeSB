package games.synx.spongysb.gui;

import ca.landonjw.gooeylibs.inventory.api.Button;
import ca.landonjw.gooeylibs.inventory.api.ButtonAction;
import ca.landonjw.gooeylibs.inventory.api.Page;
import ca.landonjw.gooeylibs.inventory.api.Template;
import com.google.common.collect.Lists;
import games.synx.pscore.util.EconomyUtil;
import games.synx.pscore.util.MessageUtil;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.config.configs.Messages;
import games.synx.spongysb.config.configs.Upgrades;
import games.synx.spongysb.config.configs.wrappers.IslandUpgradeWrapper;
import games.synx.spongysb.objects.*;
import games.synx.spongysb.objects.enums.IslandPerm;
import games.synx.spongysb.objects.enums.UpgradeType;
import games.synx.spongysb.util.IslandUtil;
import games.synx.spongysb.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.economy.transaction.ResultType;
import org.spongepowered.api.service.economy.transaction.TransactionResult;

import java.math.BigDecimal;
import java.util.List;

public class IslandUpgradesGUI {

    public static void open(Player player) {

        final Messages.MessageSettings MESSAGES = ConfigManager.get().getMessages();

        SPlayer sPlayer = SPlayer.get(player);
        Island island = sPlayer.getIsland();

        Upgrades.UpgradeSettings upgradesGUI = ConfigManager.get().getUpgrades();

        Template.Builder template = Template.builder(upgradesGUI.rows).fill(upgradesGUI.fillerSlot.getFillerButton());

        for (Upgrades.UpgradeSettings.UpgradeButton confButton : upgradesGUI.buttons) {

            Button button = Button.builder()
                    .item(confButton.getItemStack())
                    .displayName(confButton.displayName)
                    .lore(replaceLevel(confButton, island))
                    .onClick((action) -> {
                        if (!sPlayer.hasPerm(IslandPerm.SET_UPGRADES, sPlayer.getIsland())) {
                            MessageUtil.msg(player, MESSAGES.upgrades.cannot_upgrade);
                            return;
                        }

                        buttonClick(MESSAGES, confButton, action, player);

                    }).build();

            template.set(confButton.row, confButton.column, button);
        }

        Page.builder()
                .template(template.build())
                .title(upgradesGUI.menuTitle)
                .build()
                .openPage((EntityPlayerMP) player);

    }


    private static void buttonClick(Messages.MessageSettings messageSettings, Upgrades.UpgradeSettings.UpgradeButton upgradeButton, ButtonAction action, Player player) {

        SPlayer sPlayer = SPlayer.get(player);
        Island island = sPlayer.getIsland();

        IslandUpgradeWrapper newLevel = null;

        if (upgradeButton.upgradeType == UpgradeType.SIZE) {
            newLevel = upgradeButton.tiers.get(String.valueOf(Integer.valueOf(island.getIslandSizeValue() + 1)));
        }

        if (upgradeButton.upgradeType == UpgradeType.GENERATOR) {
            newLevel = upgradeButton.tiers.get(String.valueOf(Integer.valueOf(island.getIslandGeneratorValue() + 1)));
        }

        if(upgradeButton.upgradeType == UpgradeType.MEMBER_LIMIT) {
            newLevel = upgradeButton.tiers.get(String.valueOf(Integer.parseInt(island.getIslandMemberLimitValue()) + 1));
        }


        if (newLevel == null) {
            MessageUtil.msg(player, messageSettings.upgrades.max_upgrade);
            return;
        }

        TransactionResult result = EconomyUtil.withdrawBalance(player, BigDecimal.valueOf(newLevel.cost));
        if (result.getResult() == ResultType.SUCCESS) {

            if(upgradeButton.upgradeType == UpgradeType.SIZE) {
                island.setSize(String.valueOf(Integer.parseInt(island.getIslandSizeValue()) + 1));
                for (Player aPlayer : PlayerUtil.getAllPlayersAtIsland(island)) {
                    IslandUtil.changeBorder(aPlayer, island.getCenterLocation());
                }
            }

            if(upgradeButton.upgradeType == UpgradeType.GENERATOR) {
                island.setIslandGeneratorValue(Integer.parseInt(island.getIslandGeneratorValue() + 1));
            }

            if (upgradeButton.upgradeType == UpgradeType.MEMBER_LIMIT) {
                island.setMemberLimitValue(Integer.parseInt(island.getIslandMemberLimitValue() + 1));
            }

            island.broadcastToOnlineMembers(messageSettings.upgrades.upgraded, player.getName());
            action.getButton().toBuilder().lore(replaceLevel(upgradeButton, island)).build();

        }
        if (result.getResult() == ResultType.FAILED || result.getResult() == ResultType.ACCOUNT_NO_FUNDS) {
            MessageUtil.msg(player, messageSettings.upgrades.no_funds);
        }
    }

    private static List<String> replaceLevel(Upgrades.UpgradeSettings.UpgradeButton upgradeButton, Island island) {

        IslandUpgradeWrapper button = null;
        String setting = "";
        String cost = "";

        if (upgradeButton.upgradeType == UpgradeType.SIZE) {
            button = upgradeButton.tiers.get(island.getIslandSizeValue());
        }
        if (upgradeButton.upgradeType == UpgradeType.GENERATOR) {
            button = upgradeButton.tiers.get(island.getIslandGeneratorValue());
        }
        if(upgradeButton.upgradeType == UpgradeType.MEMBER_LIMIT) {
            button = upgradeButton.tiers.get(island.getIslandMemberLimitValue());
        }

        if (button == null) {
            return null;
        }

        setting = String.valueOf(button.getSetting());
        cost = String.valueOf(button.getCost());


        List<String> lore = Lists.newArrayList();
        for (String s : button.getDescription()) {
            lore.add(s.replace("{setting}", setting)
                    .replace("{cost}", cost));
        }
        return lore;
    }

}
