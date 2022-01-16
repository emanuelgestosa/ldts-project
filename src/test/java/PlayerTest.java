import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.InputMismatchException;
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
    private Robot robot;
    private KeyStroke keyStroke;


    @BeforeEach
    private void helper() throws IOException, AWTException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        terminal = Mockito.mock(Terminal.class);
        dealer = Mockito.mock(Dealer.class);
        deck = Mockito.mock(Deck.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gui = new LanternaGUI(screen);
        player = new Player("", 100);
        robot =  new Robot();
        keyStroke = Mockito.mock(KeyStroke.class);

    }

    @Test
    public void scanInputTest() throws IOException {

        int result = player.scanInput();

        // NAO SEI COMO TESTAR ISTO
    }

}
