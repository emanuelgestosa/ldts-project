import java.io.IOException;

public class Game {

    private Table table;
    private Menu menu;
    private final LanternaGUI gui;

    private static Game instance = null;

    private Game() throws IOException {
        gui = new LanternaGUI();
        menu = new Menu();
        table = new Table("Domingos", 50, 10);
    }

    public static Game getInstance() throws IOException {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void run() throws IOException {
        while(true) {
            gui.clear();
            menu.draw(gui);
            gui.refresh();

            int key = menu.proccessKey(gui.getKey());
            if (key == -1) {
                gui.close();
                return;
            }
            else if (key == 1) break;
        }
        gui.clear();
        table.play();
        table.draw(gui);
        gui.refresh();
    }
}

class GameEntryPoint {
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
        game.run();
    }
}