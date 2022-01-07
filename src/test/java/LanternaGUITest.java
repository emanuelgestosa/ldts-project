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
    private TextGraphics graphics;

    @BeforeEach
    private void helper() throws IOException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gui = new LanternaGUI(screen);
    }

    @Test
    public void drawMenu() throws IOException {
        gui.drawMenu();
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 0, "(S)tart");
        Mockito.verify(graphics, Mockito.times(1)).putString(0, 1, "(Q)uit");
    }

}