package blackjack.model.game.cardholder;

public class Player extends CardHolder {

    private int balance;
    private Hand splitHand;

    public Player() {
        super();
        this.balance = 1000;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public Hand getHand() { return hand; }
    public Hand getSplitHand() { return splitHand; }
}
