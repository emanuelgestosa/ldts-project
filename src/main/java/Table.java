import java.util.ArrayList;

public class Table {

    private ArrayList<Card> cards;

    Table() {
        generateCards();
    };

    void generateCards() {

        this.cards = new ArrayList<Card>();
        for(String suit : new String[]{"Spades", "Hearts", "Diamonds", "Clubs"}) {

            for (int i = 2; i < 11; i++)
                cards.add(new Card(suit, String.valueOf(i)));

            cards.add(new Card(suit, "A"));
            cards.add(new Card(suit, "J"));
            cards.add( new Card(suit, "Q"));
            cards.add( new Card(suit, "K"));

        }
    }
}
