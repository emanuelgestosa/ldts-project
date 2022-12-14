package blackjack.viewer.game;

import blackjack.gui.GUI;
import blackjack.model.Position;
import blackjack.model.game.cardholder.Card;
import blackjack.model.game.table.Table;
import blackjack.model.menu.EndRoundMenu;
import blackjack.viewer.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EndStateViewer extends GameViewer<EndRoundMenu> {
    public EndStateViewer(EndRoundMenu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        final List<String> entries = Arrays.asList("New Round", "Exit");
        for (int i = 0; i < entries.size(); i++)
            gui.drawText(
                    new Position(gui.getWidth() / 2 - 6 + i*10, gui.getHeight() / 2),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
        drawHands(gui);
    }
}
