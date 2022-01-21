package blackjack.model.game.cardholder;

import blackjack.model.game.table.Deck;
import blackjack.model.game.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> hand;
    private int bet;

    public Hand(){
        hand = new ArrayList<Card>();
        bet = 0;
    }
    public List<Card> getCards() {
        return hand;
    }
    public void empty() {
        hand.clear();
    }
    public void addCard(Deck deck) { hand.add(deck.getCards().pop()); }
    public void addCard(Card card) { hand.add(card); }
    public int getValue() {
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
    public void setBet(int bet) {
        this.bet = bet;
        Table.getInstance().getPlayer().setBalance(Table.getInstance().getPlayer().getBalance() - bet);
    }
    public int getBet() {
        return bet;
    }
}
