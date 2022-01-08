import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.IOException;

import static java.util.UUID.fromString;

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
        player.getHand().addCard(new Card("H", "T"));
        gui.drawHand(player);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(8, 0, "AS");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#fc1111"));
        Mockito.verify(graphics, Mockito.times(1)).putString(11, 0, "TH");

    }

    @Test
    public void Hit() throws IOException {
        GUI guiMock = Mockito.mock(GUI.class);
        Game game = Game.getInstance();
        game.run();
        char a = 'a';
        //KeyStroke key = new KeyStroke(a, false, false, false);
        //when(guiMock.getKey()).thenReturn(key);

    }

}
