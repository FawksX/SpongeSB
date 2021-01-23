package games.synx.spongysb.config.configs.guis;

import games.synx.pscore.config.gui.templates.FillerButton;

import java.util.List;

public interface IGUIConfig<T> {

    String getMenuTitle();

    int getRows();

    FillerButton getFillerItem();

    List<T> getButtons();

}
