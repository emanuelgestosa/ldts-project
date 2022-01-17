import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public interface GUI {

    public void drawConfigs() throws IOException;

    public KeyStroke getKey() throws IOException;

    void drawMenu() throws IOException;

    void drawTable(int money) throws IOException;

    void drawHand(CardHolder holder) throws IOException;

    void refresh() throws IOException;

    void clear();

    void close() throws IOException;
}
