package games.synx.spongysb.config.gui;

import com.google.common.collect.Lists;
import games.synx.spongysb.config.wrapper.GUIButtonWrapper;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class GUISettings {

    @Setting
    public SchematicGUI schematicGUI = new SchematicGUI();

    @ConfigSerializable
    public static class SchematicGUI {

        @Setting
        public List<GUIButtonWrapper> buttons = Lists.newArrayList(
                new GUIButtonWrapper(1, 5, "TestItem", Lists.newArrayList("Test", "SomeList")),
                new GUIButtonWrapper(1, 7, "TestItem", Lists.newArrayList("Test", "SomeList"))
                );

    }

}
