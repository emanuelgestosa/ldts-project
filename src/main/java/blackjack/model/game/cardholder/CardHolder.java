package blackjack.model.game.cardholder;

public class CardHolder {
    protected Hand hand;

    public CardHolder() {
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