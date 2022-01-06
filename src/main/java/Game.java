import java.io.IOException;

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
        // TODO
        System.out.println("Start game");
    }
}
