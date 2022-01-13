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
}
