import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Scanner;

import java.io.IOException;

public class PlayerTest {

    private LanternaGUI gui;
    private Screen screen;
    private Terminal terminal;
    private TextGraphics graphics;
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Scanner scanner;

    @BeforeEach
    private void helper() throws IOException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        terminal = Mockito.mock(Terminal.class);
        dealer = Mockito.mock(Dealer.class);
        deck = Mockito.mock(Deck.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gui = new LanternaGUI(screen);
        player = new Player("", 100);
        scanner = Mockito.mock(Scanner.class);

    }

    @Test
    public void scannerTest() throws IOException {
        Mockito.when(scanner.nextInt()).thenReturn(4);

        player.scanInput();
        Assertions.assertEquals(4, this.player.getBet());

    }

}
