public class Dealer extends CardHolder{
    Dealer() {
        super();
    }
    public void turn(Deck deck){
        while(hand.getValue() < 17) {
            deck.GiveCardTo(this.getHand());
        }
    }
}
