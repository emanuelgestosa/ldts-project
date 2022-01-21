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
        this.player = new Player();
        player.getHand().empty();
    }

    @Test
    public void hit1() {
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "#"));
        Assertions.assertTrue(player.hit(deck, false));
    }
    @Test
    public void hit2() {
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "#"));
        player.getHand().addCard(new Card("%", "#"));
        Assertions.assertFalse(player.hit(deck, false));
    }
    @Test
    public void hit3() {
        player.getHand().addCard(new Card("%", "J"));
        player.getHand().addCard(new Card("%", "A"));
        Assertions.assertFalse(player.hit(deck, false));
    }
    @Test
    public void stand() {
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "#"));
        player.stand();
        Assertions.assertEquals(player.getHand().getCards().size(), 2);
    }
    @Test
    public void ddown1() {
        player.setBalance(1000);
        player.getHand().setBet(50);
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "A"));
        Assertions.assertTrue(player.doubleDown(deck, false));
    }
    @Test
    public void ddown2() {
        player.setBalance(1000);
        player.getHand().setBet(50);
        player.getHand().addCard(new Card("%", "6"));
        player.getHand().addCard(new Card("*", "6"));
        Assertions.assertTrue(player.doubleDown(deck, false));
    }
    @Test
    public void ddown3() {
        player.setBalance(1000);
        player.getHand().setBet(50);
        player.getHand().addCard(new Card("%", "3"));
        player.getHand().addCard(new Card("%", "A"));
        player.getHand().addCard(new Card("%", "A"));
        Assertions.assertFalse(player.doubleDown(deck, false));
    }
    @Test
    public void ddown4() {
        player.setBalance(1000);
        player.getHand().setBet(50);
        player.getHand().addCard(new Card("%", "Q"));
        player.getHand().addCard(new Card("*", "A"));
        Assertions.assertFalse(player.doubleDown(deck, false));
    }
    @Test
    public void ddown5() {
        player.setBalance(1000);
        player.getHand().setBet(600);
        player.setBalance(400);
        player.getHand().addCard(new Card("%", "Q"));
        player.getHand().addCard(new Card("*", "Q"));
        Assertions.assertFalse(player.doubleDown(deck, false));
    }
    @Test
    public void ddown6() {
        player.setBalance(1000);
        player.getHand().setBet(600);
        player.setBalance(400);
        player.getHand().addCard(new Card("%", "Q"));
        player.getHand().addCard(new Card("*", "A"));
        Assertions.assertFalse(player.doubleDown(deck, false));
    }
    @Test
    public void split1() {
        player.getHand().addCard(new Card("%", "Q"));
        player.getHand().addCard(new Card("*", "A"));
        Assertions.assertFalse(player.split(deck));
    }
    @Test
    public void split2() {
        player.getHand().addCard(new Card("%", "Q"));
        player.getHand().addCard(new Card("*", "Q"));
        Assertions.assertTrue(player.split(deck));
        Assertions.assertEquals(2, player.getHand().getCards().size());
        Assertions.assertEquals(2, player.getSplitHand().getCards().size());
    }
    @Test
    public void split3() {
        player.getHand().addCard(new Card("%", "Q"));
        player.getHand().addCard(new Card("*", "A"));
        player.getHand().addCard(new Card("*", "A"));
        Assertions.assertFalse(player.split(deck));
    }
}
