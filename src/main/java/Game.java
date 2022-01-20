import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final Menu menu;
    private final LanternaGUI gui;

    private static Game instance = null;

    private Game() throws IOException, URISyntaxException, FontFormatException {
        gui = new LanternaGUI(70, 12);
        menu = new Menu();
    }

    public static Game getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void run() throws IOException, InterruptedException {
        int nDecks = menu.run(gui);
        if (nDecks == 0) return;
        while (true) {
            gui.clear();
            Table table = new Table("Domingos", 50, nDecks);
            table.play(gui);
            table.draw(gui, table.getDealer(), table.getPlayer(), 0);
            gui.refresh();
        }
    }
}

class GameEntryPoint {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = Game.getInstance();
        game.run();
    }
}