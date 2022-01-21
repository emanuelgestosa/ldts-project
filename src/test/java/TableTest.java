import blackjack.model.game.cardholder.Card;
import blackjack.model.game.cardholder.Dealer;
import blackjack.model.game.cardholder.Player;
import blackjack.model.game.table.Deck;
import blackjack.model.game.table.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}
