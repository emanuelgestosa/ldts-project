import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public interface GUI {

    public void drawConfigs() throws IOException;

    public KeyStroke getKey() throws IOException;

    void drawMenu() throws IOException;

    void drawChip(Player player, int split)throws IOException;

    void drawTable(int money, int phase) throws IOException;

    void drawBet(int bet) throws IOException;

    public void drawSplitHand(Hand hand) throws IOException;

    void drawHand(CardHolder holder, int split) throws IOException;

    void refresh() throws IOException;

    void clear();

    void close() throws IOException;
}
