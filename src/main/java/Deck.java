import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck{
    private Stack<Card> deck;

    //Methods----------------------------------------------------------------Constructor, Getters, Setters
    public Deck(int num){
        this.deck = new Stack<Card>();
        for(int i=0; i< num; i++){
            for(String suit : new String[]{"S", "H", "D", "C"}) {
                deck.add(new Card(suit, "A"));
                for (int j = 2; j < 10; j++)
                    deck.add(new Card(suit, String.valueOf(j)));

                deck.add(new Card(suit, "T"));
                deck.add(new Card(suit, "J"));
                deck.add(new Card(suit, "Q"));
                deck.add(new Card(suit, "K"));
            }
        }
    }
    public Stack<Card> getDeck() {
        return deck;
    }
    public void setDeck(Stack<Card> deck) {
        this.deck = deck;
    }

    //Methods----------------------------------------------------------------Extra
    public void shuffle(){
        Collections.shuffle(deck);
    }
    public boolean GiveCardTo(CardHolder holder){
        if (this.isEmpty())
            return false;
        holder.getHand().addCard(deck.pop());
        return true;
    }
    public boolean isEmpty(){
        return deck.isEmpty();
    }
}