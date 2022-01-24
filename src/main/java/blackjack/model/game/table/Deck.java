package blackjack.model.game.table;


import blackjack.model.game.cardholder.Card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;

    public Deck(int num) {
        this.deck = new Stack<Card>();
        for (int i = 0; i < num; i++) {
            for (String suit : new String[]{"%", "&", "*", "$"}) {
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
                deck.add(new Card(suit, "5"));
            }
        }
    }

    public Stack<Card> getCards() {
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
