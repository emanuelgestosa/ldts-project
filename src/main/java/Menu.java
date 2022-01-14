import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Menu {

    public Menu() {
    }

    public static void draw(GUI gui) throws IOException {
        gui.drawMenu();
    }

    /*
    Return -1 to quit,
            0 to show again,
            1 to start playing.
     */
    public int processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.EOF) return -1;
        if (key.getKeyType() != KeyType.Character) return 0;
        if (Character.toLowerCase(key.getCharacter()) == 's') return 1;
        if (Character.toLowerCase(key.getCharacter()) == 'q') return -1;
        if (Character.toLowerCase(key.getCharacter()) == 'c') return 2;
        return 0;
    }

    public int processConfigKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.EOF) return -1;
        if (key.getKeyType() != KeyType.Character) return 1;
        if (Character.toLowerCase(key.getCharacter()) == 'd') return 2;
        if (Character.toLowerCase(key.getCharacter()) == 'q') return -1;
        return 1;
    }
}
