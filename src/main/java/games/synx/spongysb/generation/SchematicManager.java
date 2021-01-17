package games.synx.spongysb.generation;

import com.google.common.collect.Maps;
import games.synx.pscore.manager.AbstractManager;
import games.synx.pscore.manager.IManager;
import games.synx.spongysb.SpongySB;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class SchematicManager extends AbstractManager implements IManager {

    private static SchematicManager instance = new SchematicManager();
    public static SchematicManager get() {
        return instance;
    }

    private final Map<String, SchematicHandler> schematicHandlers = Maps.newHashMap();

    public SchematicManager() {
        super(SpongySB.get().getLogger());

        instance = this;

        Path schematics = SpongySB.get().schematicsDir;

        if(schematics.toFile().list() == null) {
            getLogger().error("SCHEMATICS COULD NOT BE FOUND!");
            getLogger().error("Island Pasting in SpongySB will not complete properly.");
        }

        for(final String file : schematics.toFile().list()) {
            schematicHandlers.put(file, new SchematicHandler(Paths.get(schematics + File.separator + file).toFile()));
        }

    }

    public Map<String, SchematicHandler> getSchematicHandlers() {
        return this.schematicHandlers;
    }

}
