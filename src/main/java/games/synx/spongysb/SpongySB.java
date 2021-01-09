package games.synx.spongysb;

import com.google.inject.Inject;
import games.synx.pscore.PSCore;
import games.synx.spongysb.cache.CoopCache;
import games.synx.spongysb.cache.IslandCache;
import games.synx.spongysb.cache.PlayerCache;
import games.synx.spongysb.commands.CommandManager;
import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.SchematicManager;
import games.synx.spongysb.generation.WorldManager;
import games.synx.spongysb.listeners.ListenerManager;
import games.synx.spongysb.storage.DatabaseManager;
import org.slf4j.Logger;
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
        @Dependency(id="nucleus"),
        @Dependency(id="pscore")
    })
public class SpongySB {

    private static SpongySB instance;

    private Path configDir;
    public Path schematicsDir;

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
        instance = this;


    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {

        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.DARK_GRAY, "------------------------------"));
        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.YELLOW, TextStyles.BOLD, "SpongeSB by FawksX"));
        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.WHITE, "Licensed to ", TextColors.RED, "Blaze", TextColors.YELLOW, "Gaming"));
        Sponge.getServer().getConsole().getCommandSource().get().sendMessage(Text.of(TextColors.DARK_GRAY, "------------------------------"));

        this.setupConfigDirectories();

        new ConfigManager();
        new DatabaseManager();
        new WorldManager();
        IslandCache.setup();
        CoopCache.setup();
        new ListenerManager();
        new CommandManager();
        new SchematicManager();

        IslandCache.autosave();
        CoopCache.autosave();
        PlayerCache.autosave();

    }

    @Listener
    public void onServerStop(GameStoppingServerEvent event) {
        CoopCache.shutdown();
        IslandCache.shutdown();
        PlayerCache.shutdown();
    }

    private void setupConfigDirectories() {
        this.configDir = PSCore.getConfigManager().setupDirectory("SpongeSB");

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


}
