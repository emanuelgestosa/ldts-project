import blackjack.model.game.cardholder.Card;
import blackjack.model.game.cardholder.Player;
import blackjack.model.game.table.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    private Deck deck;

    @BeforeEach
    private void helper() {
        deck = new Deck(8);
        this.player = new Player(deck);
        player.getHand().empty();
    }

    @Test
    public void hit1() {
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "#"));
        Assertions.assertTrue(player.hit(deck));
    }
    @Test
    public void hit2() {
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "#"));
        player.getHand().addCard(new Card("%", "#"));
        Assertions.assertFalse(player.hit(deck));
    }
    @Test
    public void hit3() {
        player.getHand().addCard(new Card("%", "J"));
        player.getHand().addCard(new Card("%", "A"));
        Assertions.assertFalse(player.hit(deck));
    }
    @Test
    public void stand() {
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "#"));
        Assertions.assertEquals(player.getHand().getCards().size(), 2);
    }
}
