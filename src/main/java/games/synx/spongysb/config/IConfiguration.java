package games.synx.spongysb.config;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.objectmapping.ObjectMapper;
import org.spongepowered.configurate.serialize.SerializationException;

import java.nio.file.Path;

public interface IConfiguration {

  @NonNull Object loadConfiguration(ObjectMapper<?> objectMapper, final ConfigurationNode node) throws ConfigurateException;

  void saveConfiguration(final Object configuration, final ConfigurationNode node) throws SerializationException;
  void setup();

  Path getConfigFile();

}
