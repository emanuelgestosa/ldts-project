import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class PlayerTest {

    private LanternaGUI gui;
    private Screen screen;
    private Terminal terminal;
    private TextGraphics graphics;
    private Player player;
    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    private void helper() throws IOException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        terminal = Mockito.mock(Terminal.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(terminal.getTerminalSize()).thenReturn(new TerminalSize(40, 15 + 1));
        gui = new LanternaGUI(screen, terminal);
        player = new Player("", 100);
        dealer = new Dealer();
        deck = new Deck(30);
    }

   @Test
   public void PlayerTest() {
        boolean test = player.processKeyBet(gui, dealer, deck, '2', player.hand);
        Assertions.assertEquals(true, test);
        test = player.processKeyBet(gui, dealer, deck, '5', player.hand);
        Assertions.assertEquals(false, test);
        Assertions.assertEquals(10, this.player.getBet());
        Assertions.assertEquals(90, this.player.getMoney());
   }


}
