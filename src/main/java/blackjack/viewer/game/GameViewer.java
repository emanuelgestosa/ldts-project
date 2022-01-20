package blackjack.viewer.game;

import blackjack.gui.GUI;
import blackjack.model.Position;
import blackjack.model.game.table.Table;
import blackjack.viewer.Viewer;

import java.io.IOException;

public class GameViewer extends Viewer<Table> {

    public GameViewer(Table table) {
        super(table);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(3 + i*15, gui.getHeight() - 1),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}
