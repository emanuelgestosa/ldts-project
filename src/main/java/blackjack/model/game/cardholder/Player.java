package blackjack.model.game.cardholder;

import blackjack.model.game.table.Deck;

public class Player extends CardHolder {

    private int balance;
    private Hand splitHand;

    public Player(Deck deck) {
        super(deck);
        this.balance = 1000;
        hand.addCard(deck);
        hand.addCard(deck);
    }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance;  }
    public Hand getSplitHand() { return splitHand; }

    public boolean hit(Deck deck) {
        if (hand.getValue() < 21) {
            hand.addCard(deck);
            return true;
        }
        return false;
    }
    public void stand() {}

    public boolean doubleDown(Deck deck) {
        if (hand.getCards().size() != 2 || hand.getValue() >= 21) return false;
        hand.addCard(deck);
        balance -= hand.getBet();
        hand.setBet(hand.getBet() * 2);
        return true;
    }
}
