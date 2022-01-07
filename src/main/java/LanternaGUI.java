import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class LanternaGUI implements GUI{

    private Screen screen;
    private Terminal terminal;

    public LanternaGUI() throws IOException {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LanternaGUI(Screen screen) throws IOException {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = screen;
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public KeyStroke getKey() throws IOException {
        return screen.readInput();
    }

    @Override
    public void drawMenu() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.putString(0, 0, "(S)tart");
        graphics.putString(0, 1, "(Q)uit");
    }

    @Override
    public void drawTable() throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2d8c17"));
        graphics.fillRectangle(new TerminalPosition(0, 0), terminal.getTerminalSize(), ' ');
    }

    @Override
    public void drawHand(CardHolder holder) throws IOException {

    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
