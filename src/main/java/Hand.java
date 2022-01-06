import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;
    Hand(){
        hand = new ArrayList<Card>();
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
}
