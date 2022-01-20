package blackjack.model.game.table;

import blackjack.model.game.cardholder.Dealer;
import blackjack.model.game.cardholder.Player;

import java.util.Arrays;
import java.util.List;

public class Table {

    private final Player player;
    private final Dealer dealer;
    private final Deck deck;
    private final List<String> entries;
    private int currentEntry = 0;

    private static Table instance = null;

    private Table() {
        deck = new Deck(8);
        deck.shuffle();
        player = new Player(deck);
        dealer = new Dealer(deck);
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
