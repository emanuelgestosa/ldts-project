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

    private void draw() throws IOException {
        gui.clear();
        table.draw(gui);
        gui.refresh();
    }

    public void run() throws IOException {
        draw();
    }
}
