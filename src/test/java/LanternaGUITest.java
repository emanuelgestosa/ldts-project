import blackjack.gui.LanternaGUI;
import blackjack.model.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics graphics;

    @BeforeEach
    void helper() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gui = new LanternaGUI(screen);
    }
    @Test
    void drawText() {
        gui.drawText(new Position(1, 1), "asasaas", "#111111");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#111111"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, "asasaas");
    }
}
