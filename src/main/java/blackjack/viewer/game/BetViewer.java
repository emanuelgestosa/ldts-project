package blackjack.viewer.game;

import blackjack.gui.GUI;
import blackjack.model.Position;
import blackjack.model.game.table.Table;
import blackjack.model.menu.BetMenu;
import blackjack.viewer.Viewer;

import java.io.IOException;

public class BetViewer extends Viewer<BetMenu> {

    public BetViewer(BetMenu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        gui.drawText(
                new Position(gui.getWidth() / 2 - 5, gui.getHeight() / 2 - 2),
                "Balance:" + Table.getInstance().getPlayer().getBalance(),
                "#FFFFFF");
        gui.drawText(
                new Position(gui.getWidth() / 2 - 6, gui.getHeight() / 2 - 1),
                "Enter your bet:",
                "#FFFFFF");
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(gui.getWidth() / 2 - 13 + i*5, gui.getHeight() / 2),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}
