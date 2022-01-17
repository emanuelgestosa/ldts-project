import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Table table;
    private Menu menu;
    private int nDecks = 8;
    private final LanternaGUI gui;



    private static Game instance = null;

    private Game() throws IOException {
        gui = new LanternaGUI(65, 15);
        menu = new Menu();
        table = new Table("Domingos", 50, nDecks);
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

            int key = menu.processKey(gui.getKey());
            if (key == -1) {
                gui.close();
                return;
            }
            else if (key == 1) break;
            else if (key ==2) {
                configs();
            }
        }
        gui.clear();
        table.play(gui);
        table.draw(gui, table.getDealer(),table.getPlayer());
        gui.refresh();
    }

    public Table getTable() {
        return table;
    }

    public void configs() throws IOException{
        gui.clear();
        gui.drawConfigs();
        gui.refresh();
        int key = 1;
        while (key != -1){
            key = menu.processConfigKey(gui.getKey());
            if(key == 2) {
                KeyStroke nDecks = gui.getKey();
                if ((int) nDecks.getCharacter() < 49 || (int) nDecks.getCharacter() > 57) {
                    //invalid
                } else {
                    this.nDecks = (int) nDecks.getCharacter() - 48;
                    break;
                }
            }
        }
    }
}

class GameEntryPoint {
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
        game.run();
    }
}