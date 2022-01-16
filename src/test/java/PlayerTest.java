import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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

    private Player player;

    @BeforeEach
    private void helper() throws IOException {
        player = new Player("", 100);
    }

    @Test
    public void scannerTest() throws IOException {
        player.scanInput(4);
        Assertions.assertEquals(4, this.player.getBet());
    }
}
