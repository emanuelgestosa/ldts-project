package blackjack.model.game.cardholder;

import blackjack.model.game.table.Deck;

public class CardHolder {
    protected Hand hand;

    public CardHolder(Deck deck) {
        hand = new Hand();
    }
    public Hand getHand(){
        return hand;
    }
    public void emptyHand(){
        hand.empty();
    }
    public boolean hasBlackjack() {
        return hand.getValue() == 21 && hand.getCards().size() == 2;
    }
}