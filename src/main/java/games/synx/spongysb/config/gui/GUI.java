package games.synx.spongysb.config.gui;

import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public class GUI extends AbstractConfiguration<GUISettings> implements IConfiguration {

    public GUI(Path configFile) throws IOException {
        super(configFile, GUISettings.class);
    }

}
