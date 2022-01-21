import blackjack.model.game.table.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TableTest {
    private Table table;

    @BeforeEach
    private void helper() {
        table = Mockito.mock(Table.class);
    }

    @Test
    public void prepareNewRound() {
        table.prepareNewRound();
        Assertions.assertEquals(2, table.getPlayer().getHand().getCards().size());
        Assertions.assertEquals(1, table.getDealer().getHand().getCards().size());
        Assertions.assertEquals(0, table.getPlayer().getHand().getBet());
    }
}
