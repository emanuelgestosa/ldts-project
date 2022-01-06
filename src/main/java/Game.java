import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

public class Game {

    private Table table;
    private final LanternaGUI gui;

    public Game() throws IOException {
        gui = new LanternaGUI();
        table = new Table("Domingos", 50, 10);
    }

    public void run() {
        // TODO
    }
}
