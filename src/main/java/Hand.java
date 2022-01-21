import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;
    private int bet=0;

    Hand(){
        hand = new ArrayList<Card>();
    }
    public List<Card> getHand() {
        return hand;
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public int getValue(){
        int totValue=0;
        int acesAs11=0;
        for(Card card : hand){
            int cardValue = card.getValue();
            if (cardValue == 11)
                acesAs11++;
            totValue+=cardValue;
        }
        while(totValue > 21 && acesAs11 > 0){
            totValue -= 10;
            acesAs11--;
        }
        return totValue;
    }
    public List<Card> getCards(){return hand;}
    public Card getCardAt(int index){
        return hand.get(index);
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}
