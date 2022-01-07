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
        gui = new LanternaGUI();
        screen = Mockito.mock(Screen.class);
        terminal = Mockito.mock(Terminal.class);
        graphics = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void drawMenu() throws IOException {
        boolean expected = true;
        gui.drawMenu();
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 0, "(S)tart");
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 0, "(Q)uit");
    }

}
