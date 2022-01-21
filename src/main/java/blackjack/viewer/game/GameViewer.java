package blackjack.viewer.game;

import blackjack.gui.GUI;
import blackjack.model.Position;
import blackjack.model.game.cardholder.Card;
import blackjack.model.game.table.Table;
import blackjack.viewer.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameViewer extends Viewer<Table> {

    public GameViewer(Table table) {
        super(table);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(3 + i * 15, gui.getHeight() - 1),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
        drawHands(gui);
    }

    private void drawHands(GUI gui) throws IOException {
        drawPlayerHand(gui);
        if (getModel().getPlayer().isSplit()) drawPlayerSplitHand(gui);
        drawDealerHand(gui);
    }

    private void drawPlayerHand(GUI gui) throws IOException {
        List<Card> cards = new ArrayList<Card>(getModel().getPlayer().getHand().getCards());
        int drawColumn = (gui.getWidth() - cards.size()) / (2 + (getModel().getPlayer().isSplit() ? 2 : 0));
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            gui.drawText(
                    new Position(drawColumn + i*3, gui.getHeight() - 4),
                    card.getSymbol() + card.getSuit(),
                    Objects.equals(card.getSuit(), "&") || Objects.equals(card.getSuit(), "%") ? "#fc1111" : "#FFFFFF"
            );
        }
        gui.drawText(
                new Position(drawColumn + cards.size(), gui.getHeight() - 3),
                Integer.toString(getModel().getPlayer().getHand().getValue()),
                "#29aa4b"
        );
        gui.drawText(
                new Position(drawColumn + cards.size()*3, gui.getHeight() - 5),
                Integer.toString(getModel().getPlayer().getHand().getBet()),
                "#c7db13"
        );
    }

    private void drawPlayerSplitHand(GUI gui) throws IOException {
        List<Card> cards = new ArrayList<Card>(getModel().getPlayer().getSplitHand().getCards());
        int drawColumn = (gui.getWidth() - cards.size()) / 4 * 3;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            gui.drawText(
                    new Position(drawColumn + i*3, gui.getHeight() - 4),
                    card.getSymbol() + card.getSuit(),
                    Objects.equals(card.getSuit(), "&") || Objects.equals(card.getSuit(), "%") ? "#fc1111" : "#FFFFFF"
            );
        }
        gui.drawText(
                new Position(drawColumn + cards.size(), gui.getHeight() - 3),
                Integer.toString(getModel().getPlayer().getSplitHand().getValue()),
                "#29aa4b"
        );
        gui.drawText(
                new Position(drawColumn + cards.size()*3, gui.getHeight() - 5),
                Integer.toString(getModel().getPlayer().getSplitHand().getBet()),
                "#c7db13"
        );
    }

    private void drawDealerHand(GUI gui) throws IOException {
        List<Card> cards = new ArrayList<Card>(getModel().getDealer().getHand().getCards());
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
                Integer.toString(getModel().getDealer().getHand().getValue()),
                "#29aa4b"
        );
    }
}
