package games.synx.spongysb.config;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.nio.file.Path;

public interface IConfiguration {

  @NonNull Object loadConfiguration(ObjectMapper<?> objectMapper, final ConfigurationNode node) throws ObjectMappingException;

  void saveConfiguration(final Object configuration, final ConfigurationNode node) throws ObjectMappingException;
  void setup();

  Path getConfigFile();

}