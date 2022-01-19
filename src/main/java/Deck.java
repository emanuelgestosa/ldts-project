import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck{
    private Stack<Card> deck;

    //Methods----------------------------------------------------------------Constructor, Getters, Setters
    public Deck(int num){
        this.deck = new Stack<Card>();
        for(int i=0; i< num; i++){
            for(String suit : new String[]{"%", "&", "*", "$"}) {
                deck.add(new Card(suit, "A"));
                deck.add(new Card(suit, "2"));
                deck.add(new Card(suit, "3"));
                deck.add(new Card(suit, "4"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "6"));
                deck.add(new Card(suit, "7"));
                deck.add(new Card(suit, "8"));
                deck.add(new Card(suit, "9"));
                deck.add(new Card(suit, "#"));
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

    public boolean GiveCardTo(Hand hand){
        if (this.isEmpty())
            return false;
        hand.addCard(deck.pop());
        return true;
    }
    public boolean isEmpty(){
        return deck.isEmpty();
    }
}
