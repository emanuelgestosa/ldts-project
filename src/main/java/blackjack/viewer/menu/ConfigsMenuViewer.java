package blackjack.viewer.menu;

import blackjack.gui.GUI;
import blackjack.model.Position;
import blackjack.model.menu.ConfigsMenu;
import blackjack.viewer.Viewer;

import java.io.IOException;

public class ConfigsMenuViewer extends Viewer<ConfigsMenu> {
    public ConfigsMenuViewer(ConfigsMenu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.drawText(new Position(28, 4), "Number of decks:", "#FFFFFF");
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(27 + i*2, 5),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}
