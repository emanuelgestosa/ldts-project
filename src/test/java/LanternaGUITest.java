import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class LanternaGUITest {

    private LanternaGUI gui;
    private Screen screen;
    private Terminal terminal;
    private TextGraphics graphics;

    @BeforeEach
    private void helper() throws IOException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        terminal = Mockito.mock(Terminal.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gui = new LanternaGUI(screen);
    }

    @Test
    public void getKey() throws IOException {
        gui.getKey();
        Mockito.verify(screen, Mockito.times(1)).readInput();
    }

    @Test
    public void drawMenu() throws IOException {
        gui.drawMenu();
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 0, "(S)tart");
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 1, "(Q)uit");
    }

    @Test
    public void drawTable() throws IOException {
        gui.drawTable();
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
    }

    @Test
    public void drawHand() throws IOException {
        Player player = new Player("", 100);
        player.getHand().addCard(new Card("S", "A"));
        gui.drawHand(player);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 0, "AS");
    }

}
