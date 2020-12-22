package games.synx.spongysb;

import com.google.inject.Inject;
import games.synx.spongysb.config.ConfigManager;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Plugin(
    id = "spongysb",
    name = "SpongySB",
    authors = "FawksX",
    version = "1.0.0",
    description = "Sponge Skyblock Core",
    dependencies = {

    })
public class SpongySB {

    private static SpongySB instance;

    private Path configDir;

    private ConfigManager configManager;

    // ----------------------------------------------- //
    // SPONGE DEPENDENCY INJECTIONS
    // ----------------------------------------------- //

    @Inject
    private PluginContainer pluginContainer;

    @Inject
    private Game game;

    @Inject
    private Logger logger;

    @Inject @ConfigDir(sharedRoot = false)
    private Path defaultConfigDir;

    // ----------------------------------------------- //
    // METHODS
    // ----------------------------------------------- //


    @Listener
    public void onAboutToStart(GameAboutToStartServerEvent event) {
        instance = this;
        this.setupConfigDirectories();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {

        configManager = new ConfigManager();

    }

    private void setupConfigDirectories() {
        this.configDir = Paths.get("SpongeSB" + File.separator);

        if (!this.configDir.toFile().exists()) {
            this.configDir.toFile().mkdir();
        }

        if (!this.defaultConfigDir.toFile().exists()) {
            this.defaultConfigDir.toFile().mkdir();
        }
    }

    // ----------------------------------------------- //
    // GETTERS AND SETTERS
    // ----------------------------------------------- //

    public static SpongySB get() {
        return instance;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public Game getGame() {
        return this.getGame();
    }

    public Path getDefaultConfigDir() {
        return this.defaultConfigDir;
    }

    public Path getConfigDir() {
        return this.configDir;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

}
