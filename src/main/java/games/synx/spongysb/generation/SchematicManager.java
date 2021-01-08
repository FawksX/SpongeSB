package games.synx.spongysb.generation;

import com.google.common.collect.Maps;
import games.synx.spongysb.SpongySB;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class SchematicManager {

    private static SchematicManager instance = new SchematicManager();
    public static SchematicManager get() {
        return instance;
    }

    private Logger logger = SpongySB.get().getLogger();

    private Map<String, SchematicHandler> schematicHandlers = Maps.newHashMap();

    public SchematicManager() {
        logger.info("Registering SchematicManager");

        instance = this;

        Path schematics = Paths.get(SpongySB.get().schematicsDir.toString());

        if(schematics.toFile().list() == null) {
            logger.error("SCHEMATICS COULD NOT BE FOUND!");
            logger.error("Island Pasting in SpongySB will not complete properly.");
        }

        for(final String file : schematics.toFile().list()) {
            schematicHandlers.put(file, new SchematicHandler(Paths.get(SpongySB.get().schematicsDir + File.separator + file).toFile()));
        }

    }

    public Map<String, SchematicHandler> getSchematicHandlers() {
        return this.schematicHandlers;
    }

}
