public abstract class CardHolder {
    protected Hand hand;

    CardHolder() {
        hand = new Hand();
    }
    public Hand getHand(){
        return hand;
    }
}
