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

public class EndStateViewer extends Viewer<EndRoundMenu> {
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
    private void drawHands(GUI gui) throws IOException {
        drawPlayerHand(gui);
        drawDealerHand(gui);
    }
    private void drawPlayerHand(GUI gui) throws IOException {
        List<Card> cards = new ArrayList<Card>(Table.getInstance().getPlayer().getHand().getCards());
        int drawColumn = (gui.getWidth() - cards.size()) / 2;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            gui.drawText(
                    new Position(drawColumn + i*3, gui.getHeight() - 4),
                    card.getSymbol() + card.getSuit(),
                    Objects.equals(card.getSuit(), "&") || Objects.equals(card.getSuit(), "%") ? "#fc1111" : "#FFFFFF"
            );
        }
        gui.drawText(
                new Position(gui.getWidth() / 2 + 1, gui.getHeight() - 3),
                Integer.toString(Table.getInstance().getPlayer().getHand().getValue()),
                "#29aa4b"
        );
        gui.drawText(
                new Position(drawColumn + cards.size()*3, gui.getHeight() - 5),
                Integer.toString(Table.getInstance().getPlayer().getHand().getBet()),
                "#c7db13"
        );
    }
    private void drawDealerHand(GUI gui) throws IOException {
        List<Card> cards = new ArrayList<Card>(Table.getInstance().getDealer().getHand().getCards());
        if (cards.size() < 2) cards.add(new Card("?", "?"));
        int drawColumn = (gui.getWidth() - cards.size()) / 2;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            gui.drawText(
                    new Position(drawColumn + i*3, 2),
                    card.getSymbol() + card.getSuit(),
                    Objects.equals(card.getSuit(), "&") || Objects.equals(card.getSuit(), "%") ? "#fc1111" : "#FFFFFF"
            );
        }
        gui.drawText(
                new Position(gui.getWidth() / 2 + 1, 1),
                Integer.toString(Table.getInstance().getDealer().getHand().getValue()),
                "#29aa4b"
        );
    }
}
