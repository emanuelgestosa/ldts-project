package blackjack.model.game.cardholder;

import blackjack.model.game.table.Deck;

import java.util.Objects;

public class Player extends CardHolder {

    private int balance;
    private Hand splitHand;

    public Player() {
        super();
        this.balance = 1000;
        splitHand = new Hand();
    }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance;  }
    public Hand getSplitHand() { return splitHand; }

    public boolean hit(Deck deck, boolean split) {
        if (!split) {
            if (hand.getValue() < 21) {
                hand.addCard(deck);
                return true;
            }
        }
        else {
            if (splitHand.getValue() < 21) {
                splitHand.addCard(deck);
                return true;
            }
        }
        return false;
    }
    public void stand() {}

    public boolean doubleDown(Deck deck, boolean split) {
        if (!split) {
            if (hand.getCards().size() != 2 || hand.getValue() >= 21 || hand.getBet() > balance) return false;
            hand.addCard(deck);
            balance -= hand.getBet();
            hand.setBet(hand.getBet() * 2);
        }
        else {
            if (splitHand.getCards().size() != 2 || splitHand.getValue() >= 21 || splitHand.getBet() > balance) return false;
            splitHand.addCard(deck);
            balance -= splitHand.getBet();
            splitHand.setBet(splitHand.getBet() * 2);
        }
        return true;
    }
    public boolean split(Deck deck) {
        if (isSplit() ||
                hand.getCards().size() > 2 ||
                !Objects.equals(hand.getCards().get(0).getSymbol(), hand.getCards().get(1).getSymbol()))
            return false;
        splitHand.getCards().add(hand.getCards().get(1));
        hand.getCards().remove(1);
        hand.addCard(deck);
        splitHand.addCard(deck);
        splitHand.setBet(hand.getBet());
        balance -= hand.getBet();
        return true;
    }
    public boolean isSplit() {
        return splitHand.getCards().size() != 0;
    }

}
