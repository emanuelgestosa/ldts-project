import blackjack.model.game.cardholder.Card;
import blackjack.model.game.cardholder.Dealer;
import blackjack.model.game.cardholder.Player;
import blackjack.model.game.table.Deck;
import blackjack.model.game.table.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TableTest {
    private Table table;

    @BeforeEach
    private void helper() {
        table = Table.getInstance();
        table.getPlayer().getHand().addCard(new Card("%", "A"));
        table.getDealer().getHand().addCard(new Card("*", "2"));
        table.getPlayer().getHand().setBet(500);
    }

    @Test
    public void prepareNewRound() {
        table.prepareNewRound();
        Assertions.assertEquals(0, table.getPlayer().getHand().getCards().size());
        Assertions.assertEquals(0, table.getDealer().getHand().getCards().size());
        Assertions.assertEquals(0, table.getPlayer().getHand().getBet());
    }
    @Test
    public void dealCards() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.dealCards();
        Assertions.assertEquals(2, table.getPlayer().getHand().getCards().size());
        Assertions.assertEquals(1, table.getDealer().getHand().getCards().size());
    }
    @Test
    public void calcWinnings1() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "8"));
        table.getPlayer().getHand().addCard(new Card("*", "A"));
        table.getDealer().getHand().addCard(new Card("%", "8"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(2, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings2() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "8"));
        table.getPlayer().getHand().addCard(new Card("*", "A"));
        table.getDealer().getHand().addCard(new Card("%", "Q"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(0, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings3() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "8"));
        table.getPlayer().getHand().addCard(new Card("*", "A"));
        table.getDealer().getHand().addCard(new Card("%", "9"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(1, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings4() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "K"));
        table.getPlayer().getHand().addCard(new Card("*", "A"));
        table.getDealer().getHand().addCard(new Card("%", "Q"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(2.5f, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings5() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "9"));
        table.getPlayer().getHand().addCard(new Card("*", "A"));
        table.getDealer().getHand().addCard(new Card("%", "A"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(0, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings6() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "Q"));
        table.getPlayer().getHand().addCard(new Card("*", "A"));
        table.getDealer().getHand().addCard(new Card("%", "A"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(1, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings7() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "9"));
        table.getPlayer().getHand().addCard(new Card("*", "K"));
        table.getPlayer().getHand().addCard(new Card("%", "5"));
        table.getDealer().getHand().addCard(new Card("*", "A"));
        table.getDealer().getHand().addCard(new Card("%", "6"));
        Assertions.assertEquals(0, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings8() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "9"));
        table.getPlayer().getHand().addCard(new Card("*", "K"));
        table.getPlayer().getHand().addCard(new Card("*", "5"));
        table.getDealer().getHand().addCard(new Card("%", "A"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(0, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings9() {
        table.getPlayer().getHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "9"));
        table.getPlayer().getHand().addCard(new Card("*", "K"));
        table.getDealer().getHand().addCard(new Card("%", "8"));
        table.getDealer().getHand().addCard(new Card("*", "6"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(2, table.calcWinnings(table.getPlayer().getHand()));
    }
    @Test
    public void calcWinnings10() {
        table.getPlayer().getHand().empty();
        table.getPlayer().getSplitHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "9"));
        table.getPlayer().getHand().addCard(new Card("*", "K"));
        table.getPlayer().getSplitHand().addCard(new Card("*", "9"));
        table.getPlayer().getSplitHand().addCard(new Card("%", "10"));
        table.getDealer().getHand().addCard(new Card("%", "8"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(2, table.calcWinnings(table.getPlayer().getHand()));
        Assertions.assertEquals(2, table.calcWinnings(table.getPlayer().getSplitHand()));
    }
    @Test
    public void calcWinnings11() {
        table.getPlayer().getHand().empty();
        table.getPlayer().getSplitHand().empty();
        table.getDealer().getHand().empty();
        table.getPlayer().getHand().addCard(new Card("%", "9"));
        table.getPlayer().getHand().addCard(new Card("*", "K"));
        table.getPlayer().getSplitHand().addCard(new Card("*", "9"));
        table.getPlayer().getSplitHand().addCard(new Card("%", "#"));
        table.getPlayer().getSplitHand().addCard(new Card("%", "#"));
        table.getDealer().getHand().addCard(new Card("%", "8"));
        table.getDealer().getHand().addCard(new Card("*", "6"));
        table.getDealer().getHand().addCard(new Card("*", "K"));
        Assertions.assertEquals(2, table.calcWinnings(table.getPlayer().getHand()));
        Assertions.assertEquals(0, table.calcWinnings(table.getPlayer().getSplitHand()));
    }
}
