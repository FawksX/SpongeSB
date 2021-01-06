package games.synx.spongysb.config.gui;

import games.synx.spongysb.config.AbstractConfiguration;
import games.synx.spongysb.config.IConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public class GUI extends AbstractConfiguration<GUISettings> implements IConfiguration {

    public GUI(Path configFile) throws IOException {
        super(configFile, GUISettings.class);
    }

}
