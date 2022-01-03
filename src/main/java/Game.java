import java.security.PublicKey;
import java.util.ArrayList;

public class Game {
    private ArrayList<Card> cards;
    private ArrayList<String> naipes = new ArrayList<String>();

    public Game(){
        generateCards();
    }


    void generateCards(){
        naipes.add("Espada");
        naipes.add("Ouro");
        naipes.add("Copa");
        naipes.add("Paus");

        this.cards = new ArrayList<Card>();
        for(int j = 0; j < naipes.size(); j++) {
            for (int i = 2; i < 11; i++) {
                String s = String.valueOf(i);
                Card c = new Card(naipes.get(j), s);
            }

            Card As = new Card(naipes.get(j), "As");
            Card J = new Card(naipes.get(j), "J");
            Card Q = new Card(naipes.get(j), "Q");
            Card K = new Card(naipes.get(j), "K");

        }
    }
}
