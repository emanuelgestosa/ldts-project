import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface GUI {

    public void drawConfigs() throws IOException;

    public KeyStroke getKey() throws IOException;

    void drawMenu() throws IOException;

    void drawTable() throws IOException;

    void drawHand(CardHolder holder) throws IOException;

    void refresh() throws IOException;

    void clear();

    void close() throws IOException;
}
