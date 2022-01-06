import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

public class Game {

    private Table table;
    private Menu menu;
    private final LanternaGUI gui;

    public Game() throws IOException {
        gui = new LanternaGUI();
        menu = new Menu();
        table = new Table("Domingos", 50, 10);
    }

    public void run() throws IOException {
        gui.clear();
        menu.draw(gui);
        gui.refresh();
    }
}
