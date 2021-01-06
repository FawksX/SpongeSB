package games.synx.spongysb.config.gui;

import com.google.common.collect.Lists;
import games.synx.spongysb.config.wrapper.GUIButtonWrapper;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ConfigSerializable
public class GUISettings {

    @Setting
    public SchematicGUI schematicgui = new SchematicGUI();

    @ConfigSerializable
    public static class SchematicGUI {

        @Setting
        public List<GUIButtonWrapper> buttons = Stream.of(
                new GUIButtonWrapper(1, 5, "minecraft:glowstone","TestItem", Lists.newArrayList("Test", "SomeList")),
                new GUIButtonWrapper(1, 7, "minecraft:diamond_block", "TestItem", Lists.newArrayList("Test", "SomeList"))
                ).collect(Collectors.toList());

    }

}
