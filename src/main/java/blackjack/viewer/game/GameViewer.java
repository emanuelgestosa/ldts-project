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

public abstract class GameViewer<T> extends Viewer<T> {

    public GameViewer(T model) {
        super(model);
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

    private void drawPlayerSplitHand(GUI gui) throws IOException {
        List<Card> cards = new ArrayList<Card>(Table.getInstance().getPlayer().getSplitHand().getCards());
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
                Integer.toString(Table.getInstance().getPlayer().getSplitHand().getValue()),
                "#29aa4b"
        );
        gui.drawText(
                new Position(drawColumn + cards.size()*3, gui.getHeight() - 5),
                Integer.toString(Table.getInstance().getPlayer().getSplitHand().getBet()),
                "#c7db13"
        );
    }

    protected void drawHands(GUI gui) throws IOException {
        drawPlayerHand(gui);
        if (Table.getInstance().getPlayer().isSplit()) drawPlayerSplitHand(gui);
        drawDealerHand(gui);
    }

    private void drawPlayerHand(GUI gui) throws IOException {
        List<Card> cards = new ArrayList<Card>(Table.getInstance().getPlayer().getHand().getCards());
        int drawColumn = (gui.getWidth() - cards.size()) / (2 + (Table.getInstance().getPlayer().isSplit() ? 2 : 0));
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
                Integer.toString(Table.getInstance().getPlayer().getHand().getValue()),
                "#29aa4b"
        );
        gui.drawText(
                new Position(drawColumn + cards.size()*3, gui.getHeight() - 5),
                Integer.toString(Table.getInstance().getPlayer().getHand().getBet()),
                "#c7db13"
        );
    }
}
