import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Menu {

    private int nDecks;

    public Menu() {}

    public boolean run(GUI gui) throws IOException {
        while (true) {
            draw(gui);
            int key = processKey(gui.getKey());
            switch (key) {
                case -1:
                    gui.close();
                    return false;
                case 1: return true;
                case 2: configs(gui);
            }
        }
    }

    private void draw(GUI gui) throws IOException {
        gui.clear();
        gui.drawMenu();
        gui.refresh();
    }

    /*
    Return -1 to quit,
            0 to show again,
            1 to start playing,
            2 to go to configs.
     */
    private int processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.EOF) return -1;
        if (key.getKeyType() != KeyType.Character) return 0;
        if (Character.toLowerCase(key.getCharacter()) == 's') return 1;
        if (Character.toLowerCase(key.getCharacter()) == 'q') return -1;
        if (Character.toLowerCase(key.getCharacter()) == 'c') return 2;
        return 0;
    }

    private void configs(GUI gui) throws IOException {
        gui.clear();
        gui.drawConfigs();
        gui.refresh();
        int key = 1;
        while (key != -1 && key != 2) {
            key = processConfigKey(gui.getKey());
            if (key == 2) askForNDecks(gui);
        }
    }

    private void askForNDecks(GUI gui) throws IOException {
        int nDecks = 0;
        while (true) {
            gui.drawAlterDecks(nDecks);
            gui.refresh();
            KeyStroke key = gui.getKey();
            if (key.getKeyType() == KeyType.Enter && nDecks != 0) break;
            else if (key.getKeyType() == KeyType.Backspace) nDecks = 0;
            else if(key.getKeyType() == KeyType.Character && (int) key.getCharacter() > 48 && (int) key.getCharacter() <= 57) nDecks = (int) key.getCharacter() - 48;
        }
        this.nDecks = nDecks;
    }

    private int processConfigKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.EOF) return -1;
        if (key.getKeyType() != KeyType.Character) return 1;
        if (Character.toLowerCase(key.getCharacter()) == 'd') return 2;
        if (Character.toLowerCase(key.getCharacter()) == 'q') return -1;
        return 1;
    }
}
