public abstract class CardHolder {
    protected Hand hand;

    CardHolder() {
        hand = new Hand();
    }
    public Hand getHand(){
        return hand;
    }
    public void reset(){
        hand.getHand().clear();
    }
    public boolean hasBlackjack() {
        return hand.getValue() == 21 && hand.getCards().size() == 2;
    }
}
