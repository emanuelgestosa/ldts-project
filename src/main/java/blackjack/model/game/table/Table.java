package blackjack.model.game.table;

import blackjack.model.game.cardholder.Dealer;
import blackjack.model.game.cardholder.Player;

public class Table {

    private final Player player;
    private final Dealer dealer;
    private Deck deck;

    private static Table instance;

    private Table() {
        player = new Player();
        dealer = new Dealer();
        deck = new Deck(8);
    }

    public static Table getInstance() {
        if (instance == null) return new Table();
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
