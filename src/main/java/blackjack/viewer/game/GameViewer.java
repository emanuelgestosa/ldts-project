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

    }
}
