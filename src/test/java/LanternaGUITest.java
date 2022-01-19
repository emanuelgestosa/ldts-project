import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

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
        Mockito.when(terminal.getTerminalSize()).thenReturn(new TerminalSize(40, 15 + 1));
        gui = new LanternaGUI(screen, terminal);
    }

    @Test
    public void getKey() throws IOException {
        gui.getKey();
        Mockito.verify(screen, Mockito.times(1)).readInput();
    }

    @Test
    public void drawMenu() throws IOException {
        gui.drawMenu();
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), terminal.getTerminalSize(), ' ');
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#43C824"));
        Mockito.verify(graphics, Mockito.times(1)).fill('.');
        Mockito.verify(graphics, Mockito.times(1)).putString((terminal.getTerminalSize().getColumns()-"( S )tart".length()-1)/2, (terminal.getTerminalSize().getRows()/2)-2, "( S )tart");
        Mockito.verify(graphics, Mockito.times(1)).putString((terminal.getTerminalSize().getColumns()-"( C )onfigs".length()-1)/2, (terminal.getTerminalSize().getRows()/2), "( C )onfigs");
        Mockito.verify(graphics, Mockito.times(1)).putString((terminal.getTerminalSize().getColumns()-"( Q )uit".length()-1)/2, (terminal.getTerminalSize().getRows()/2)+2, "( Q )uit");
    }

    @Test
    public void drawTable() throws IOException {
        gui.drawTable(50, 0);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), terminal.getTerminalSize(), ' ');
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#43C824"));
        Mockito.verify(graphics, Mockito.times(1)).fill('.');
        Mockito.verify(graphics, Mockito.times(3)).setBackgroundColor(TextColor.Factory.fromString("#a2b536"));
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(0, terminal.getTerminalSize().getRows() - 4), new TerminalSize(terminal.getTerminalSize().getColumns(), 4), ' ');
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#753216"));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#9E4A27"));
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(20, terminal.getTerminalSize().getRows() - 4), new TerminalSize(2, 4), ' ');
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(0, terminal.getTerminalSize().getRows() - 4), new TerminalSize(terminal.getTerminalSize().getColumns(), 1), '=');
        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, terminal.getTerminalSize().getRows() - 2,"Balance: " + "50");
    }

    @Test
    public void drawHand() throws IOException {
        Player player = new Player("", 100);
        player.getHand().addCard(new Card("S", "A"));
        player.getHand().addCard(new Card("H", "T"));
        gui.drawHand(player, 0);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString((terminal.getTerminalSize().getColumns() - 6) / 2, terminal.getTerminalSize().getRows() - 6, "AS");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#fc1111"));
        Mockito.verify(graphics, Mockito.times(1)).putString((terminal.getTerminalSize().getColumns() - 6) / 2 + 3, terminal.getTerminalSize().getRows() - 6, "TH");

    }


}
