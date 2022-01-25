package blackjack.model.game.cardholder;

public class CardHolder {
    protected Hand hand;

    public CardHolder() {
        hand = new Hand();
    }
    public Hand getHand(){
        return hand;
    }
}