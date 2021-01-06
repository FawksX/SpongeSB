package games.synx.spongysb.config.wrapper.serializer;

import com.google.common.reflect.TypeToken;
import games.synx.spongysb.config.wrapper.GUIButtonWrapper;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.List;

public class GUIButtonSerializer implements TypeSerializer<GUIButtonWrapper> {
    public static final GUIButtonSerializer INSTANCE = new GUIButtonSerializer();

    private static final String ROW = "ROW";
    private static final String COLUMN = "COLUMN";
    private static final String DISPLAYNAME = "Item";
    private static final String LORE = "LORE";

    @Override
    public @Nullable GUIButtonWrapper deserialize(Type type, ConfigurationNode source) throws SerializationException {
        return new GUIButtonWrapper(
                source.node(source, ROW).getInt(),
                source.node(source, COLUMN).getInt(),
                source.node(source, DISPLAYNAME).getString(),
                source.node(source, LORE).getList(String.class)
        );
    }

    @Override
    public void serialize(Type type, @Nullable GUIButtonWrapper obj, ConfigurationNode target) throws SerializationException {

        target.node(ROW).set(obj.getRow());
        target.node(COLUMN).set(obj.getColumn());
        target.node(DISPLAYNAME).set(obj.getDisplayName());
        target.node(LORE).set(obj.getLore());


    }

    @Override
    public @Nullable GUIButtonWrapper emptyValue(Type specificType, ConfigurationOptions options) {
        return null;
    }
}
