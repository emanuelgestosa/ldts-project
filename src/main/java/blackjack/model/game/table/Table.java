package blackjack.model.game.table;

import blackjack.model.game.cardholder.Dealer;
import blackjack.model.game.cardholder.Hand;
import blackjack.model.game.cardholder.Player;

import java.util.Arrays;
import java.util.List;

public class Table {

    private final Player player;
    private final Dealer dealer;
    private Deck deck;
    private final List<String> entries;
    private int currentEntry = 0;

    private static Table instance = null;

    private Table() {
        deck = new Deck(8);
        deck.shuffle();
        player = new Player();
        dealer = new Dealer();
        this.entries = Arrays.asList("Exit", "Hit", "Stand", "DoubleDown", "Split");
    }

    public static Table getInstance() {
        if (instance == null) instance = new Table();
        return instance;
    }

    public Player getPlayer() {
        return player;
    }
    public Dealer getDealer() {
        return dealer;
    }
    public Deck getDeck() { return deck; }
    public void setNDecks(int n) { deck = new Deck(n); }
    public void clear() {
        instance = new Table();
    }
    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public void prepareNewRound() {
        float winnings = player.getHand().getBet() * calcWinnings(player.getHand()) + player.getSplitHand().getBet() * calcWinnings(player.getSplitHand());
        player.setBalance((int)(player.getBalance() + winnings));
        player.getHand().empty();
        player.getHand().setBet(0);
        dealer.getHand().empty();
        player.getSplitHand().empty();
        player.getSplitHand().setBet(0);
    }

    public float calcWinnings(Hand hand) {
        if(dealer.getHand().hasBlackjack()) {
            if (hand.hasBlackjack()) return 1;
            else return 0;
        }
        if(hand.hasBlackjack()) return 2.5f;
        if(hand.bust()) return 0;
        if(dealer.getHand().bust()) return 2;
        if(hand.getValue() > dealer.getHand().getValue()) return 2;
        if (hand.getValue() < dealer.getHand().getValue())  return 0;
        return 1;
    }

    public void dealCards() {
        player.getHand().addCard(deck);
        player.getHand().addCard(deck);
        dealer.getHand().addCard(deck);
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedExit() {
        return isSelected(0);
    }

    public boolean isSelectedHit() {
        return isSelected(1);
    }

    public boolean isSelectedStand() {
        return isSelected(2);
    }

    public boolean isSelectedDouble() {
        return isSelected(3);
    }

    public boolean isSelectedSplit() {
        return isSelected(4);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
