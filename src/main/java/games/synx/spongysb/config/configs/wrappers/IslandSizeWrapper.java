package games.synx.spongysb.config.configs.wrappers;

import com.google.common.collect.Lists;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class IslandSizeWrapper {

    @Setting
    public int cost = 0;

    @Setting
    public int size = 50;

    @Setting
    List<String> description = Lists.newArrayList(
            "§7Upgrade the ores produced by your",
            "§7Ore Generators.",
            " ",
            "§aCurrent Tier §8» §2Next Tier",
            "§8§m----------------------",
            "§fStone: §a85% §8» §285%",
            "§fCobblestone: §a15% §8» §20%",
            "§fCoal: §a0% §8» §215%",
            "§8§m----------------------",
            " ",
            "§aPrevious Cost §8» §2Next Cost",
            "§8§m----------------------",
            "§fMoney: §a$0 §8» §2$500,000",
            "§8§m----------------------"
    );

}
