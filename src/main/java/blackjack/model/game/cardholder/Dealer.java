package blackjack.model.game.cardholder;

import blackjack.model.game.table.Deck;

public class Dealer extends CardHolder {
    public Dealer(Deck deck) {
        super(deck);
        hand.addCard(deck);
    }
}
