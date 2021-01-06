package games.synx.spongysb.config;

import games.synx.spongysb.SpongySB;
import games.synx.spongysb.config.wrapper.GUIButtonWrapper;
import games.synx.spongysb.config.wrapper.serializer.GUIButtonSerializer;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;
import org.spongepowered.configurate.objectmapping.ObjectMapper;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractConfiguration<T> implements IConfiguration {

  private Class<T> clazz;

  private final ConfigurationNode node;
  private final GsonConfigurationLoader loader;
  private final Path configFile;

  private final ObjectMapper<T> MAPPER;

  private static final int CURRENT_VERSION = 2;

  private T settings;

  // ----------------------------------------------- //
  // SUPERCLASSES
  // ----------------------------------------------- //

  public AbstractConfiguration(Path configFile, Class<T> clazz) throws IOException {
    this.clazz = clazz;
    this.configFile = configFile;

     loader = GsonConfigurationLoader.builder().defaultOptions(opts -> opts.serializers(build -> build.register(GUIButtonWrapper.class, GUIButtonSerializer.INSTANCE))).path(configFile).build();

    this.node = loader.load();

    MAPPER = ObjectMapper.factory().get(clazz);

    setup();

  }

  public void saveConfiguration(final Object configuration, final ConfigurationNode node) throws SerializationException {
    MAPPER.save((T) configuration, node);
  }

  public @NonNull Object loadConfiguration(ObjectMapper<?> objectMapper, final ConfigurationNode node) throws ObjectMappingException, ConfigurateException {
    return objectMapper.load(node);
  }

  public void setup() {
    try {
      // loading

      this.settings = (T) loadConfiguration(MAPPER, getRawNode());

      // saving
      saveConfiguration(this.settings, getRawNode());
      saveRawNode();

    } catch (ObjectMappingException | ConfigurateException e) {
      e.printStackTrace();
    }
  }


  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //

  public ConfigurationNode getRawNode() {
    return this.node;
  }

  public GsonConfigurationLoader getLoader() {
    return this.loader;
  }

  public void saveRawNode() {
    try {
      getLoader().save(getRawNode());
    } catch (IOException e) {
      SpongySB.get().getLogger().error("Could not save configuration file!");
    }
  }

  public Path getConfigFile() {
    return this.configFile;
  }

  public T getSettings() {
    return this.settings;
  }

}
