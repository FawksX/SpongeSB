package games.synx.spongysb;

import com.google.inject.Inject;
import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.commands.CommandManager;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.SchematicManager;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.listeners.ListenerManager;
import games.synx.spongysb.storage.DatabaseManager;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Plugin(
    id = SpongeSBInfo.ID,
    name = SpongeSBInfo.NAME,
    authors = SpongeSBInfo.AUTHORS,
    version = SpongeSBInfo.VERSION,
    description = SpongeSBInfo.DESCRIPTION,
    dependencies = {
        @Dependency(id="nucleus")
    })
public class SpongySB {

    private static SpongySB instance;

    private Path configDir;
    public Path schematicsDir;


    private ConfigManager configManager;
    private WorldManager worldManager;
    private DatabaseManager databaseManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private SchematicManager schematicManager;

    // ----------------------------------------------- //
    // SPONGE DEPENDENCY INJECTIONS
    // ----------------------------------------------- //

    @Inject
    private PluginContainer pluginContainer;

    @Inject
    private Logger logger;

    // ----------------------------------------------- //
    // METHODS
    // ----------------------------------------------- //


    @Listener
    public void onAboutToStart(GameAboutToStartServerEvent event) {
        logger.info("Initialising Config Directories");
        instance = this;
        this.setupConfigDirectories();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {

        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.DARK_GRAY, "------------------------------"));
        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.YELLOW, TextStyles.BOLD, "SpongeSB by FawksX"));
        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.WHITE, "Licensed to ", TextColors.RED, "Blaze", TextColors.YELLOW, "Gaming"));
        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.DARK_GRAY, "------------------------------"));

        configManager = new ConfigManager();
        databaseManager = new DatabaseManager();
        worldManager = new WorldManager();
        IslandCache.setup();
        listenerManager = new ListenerManager();
        commandManager = new CommandManager();
        schematicManager = new SchematicManager();

        IslandCache.autosave();

    }

    @Listener
    public void onServerStop(GameStoppingServerEvent event) {
        IslandCache.shutdown();
    }

    private void setupConfigDirectories() {
        this.configDir = Paths.get("PixelmonSkyblock" + File.separator + "SpongeSB" + File.separator);

        if (!this.configDir.toFile().exists()) {
            this.configDir.toFile().mkdir();
        }

        this.schematicsDir = Paths.get(this.configDir + File.separator + "schematics");

        if(!this.schematicsDir.toFile().exists()) {
            this.schematicsDir.toFile().mkdir();
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

    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    public Path getConfigDir() {
        return this.configDir;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    public DatabaseManager getDatabaseManager() {
        return this.databaseManager;
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

}
