package blackjack.model.game.table;

import blackjack.model.game.cardholder.Dealer;
import blackjack.model.game.cardholder.Player;

public class Table {

    private final Player player;
    private final Dealer dealer;
    private Deck deck;

    public Table() {
        player = new Player();
        dealer = new Dealer();
        deck = new Deck(8);
    }
}
