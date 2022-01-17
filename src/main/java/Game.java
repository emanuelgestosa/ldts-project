import com.googlecode.lanterna.input.KeyStroke;

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
        table.draw(gui, table.getDealer(),table.getPlayer(), 0);
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
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = Game.getInstance();
        game.run();
    }
}