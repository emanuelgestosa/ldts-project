import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Game {
    private Table table;
    private Menu menu;
    private int nDecks = 8;
    private final LanternaGUI gui;

    private static Game instance = null;

    private Game() throws IOException, URISyntaxException, FontFormatException {
        gui = new LanternaGUI(70, 12);
        menu = new Menu();
        table = new Table("Domingos", 50, nDecks);
    }

    public static Game getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void run() throws IOException, InterruptedException {
        if (!menu.run(gui)) return;
        while(true) {
            gui.clear();
            table = new Table("Domingos", 50, nDecks);
            table.play(gui);
            table.draw(gui, table.getDealer(),table.getPlayer(), 0);
            gui.refresh();
        }
    }

    public Table getTable() {
        return table;
    }

}

class GameEntryPoint {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = Game.getInstance();
        game.run();
    }
}