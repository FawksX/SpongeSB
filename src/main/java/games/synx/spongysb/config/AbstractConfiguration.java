package games.synx.spongysb.config;

import games.synx.spongysb.SpongySB;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.transformation.ConfigurationTransformation;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractConfiguration<T> implements IConfiguration {

  private Class<T> clazz;

  private final ConfigurationNode node;
  private final GsonConfigurationLoader loader;
  private final Path configFile;

  private final ObjectMapper<T> MAPPER;

  private T settings;

  // ----------------------------------------------- //
  // SUPERCLASSES
  // ----------------------------------------------- //

  public AbstractConfiguration(Path configFile, Class<T> clazz) throws IOException {
    this.clazz = clazz;

    this.configFile = configFile;
     loader = GsonConfigurationLoader.builder()
        .setDefaultOptions(ConfigurationOptions.defaults().setShouldCopyDefaults(true))
        .setPath(configFile).build();

    this.node = loader.load();

    try {
      MAPPER = ObjectMapper.forClass(clazz);
    } catch (ObjectMappingException e) {
      throw new ExceptionInInitializerError(e);
    }

    setup();

  }

  public void saveConfiguration(final Object configuration, final ConfigurationNode node) throws ObjectMappingException {
    MAPPER.bind((T) configuration).serialize(node);
  }

  public @NonNull Object loadConfiguration(ObjectMapper<?> objectMapper, final ConfigurationNode node) throws ObjectMappingException {
    if(!node.isVirtual()) {
      final ConfigurationTransformation transformation = ConfigurationTransformation.versionedBuilder().build();
      transformation.apply(node);
    }

    return objectMapper.bindToNew().populate(node);
  }

  public void setup() {
    try {
      // loading

      this.settings = (T) loadConfiguration(MAPPER, getRawNode());

      // saving
      saveConfiguration(this.settings, getRawNode());
      saveRawNode();

    } catch (ObjectMappingException e) {
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
