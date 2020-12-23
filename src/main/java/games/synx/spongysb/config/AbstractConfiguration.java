package games.synx.spongysb.config;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.transformation.ConfigurationTransformation;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractConfiguration implements IConfiguration {

  private final ConfigurationNode node;
  private final Path configFile;

  // ----------------------------------------------- //
  // SUPERCLASSES
  // ----------------------------------------------- //

  public AbstractConfiguration(Path configFile) throws IOException {
    this.configFile = configFile;
    final GsonConfigurationLoader loader = GsonConfigurationLoader.builder()
        .setDefaultOptions(ConfigurationOptions.defaults().setShouldCopyDefaults(true))
        .setPath(configFile).build();

    this.node = loader.load();

  }

  public @NonNull Object loadConfiguration(ObjectMapper<?> objectMapper, final ConfigurationNode node) throws ObjectMappingException {
    if(!node.isVirtual()) {
      final ConfigurationTransformation transformation = ConfigurationTransformation.versionedBuilder().build();
      transformation.apply(node);
    }

    return objectMapper.bindToNew().populate(node);
  }

  // ----------------------------------------------- //
  // GETTERS
  // ----------------------------------------------- //

  public ConfigurationNode getNode() {
    return this.node;
  }

  public Path getConfigFile() {
    return this.configFile;
  }

}
