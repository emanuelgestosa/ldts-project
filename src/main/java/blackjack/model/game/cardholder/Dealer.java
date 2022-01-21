package blackjack.model.game.cardholder;

import blackjack.model.game.table.Deck;

public class Dealer extends CardHolder {
    public Dealer(Deck deck) {
        super(deck);
        hand.addCard(deck);
    }
    public void takeTurn(Deck deck) {
        while (hand.getValue() < 17) {
            hand.addCard(deck);
        }
    }
}
