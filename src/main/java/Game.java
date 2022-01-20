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
        while(true) {
            gui.clear();
            Menu.draw(gui);
            gui.refresh();

            int key = menu.processKey(gui.getKey());
            if (key == -1) {
                gui.close();
                return;
            }
            else if (key == 1) {
                gui.clear();
                table = new Table("Domingos", 50, nDecks);
                table.play(gui);
                table.draw(gui, table.getDealer(),table.getPlayer(), 0);
                gui.refresh();
            }
            else if (key ==2) {
                configs();
            }
        }
    }

    public void configs() throws IOException{
        gui.clear();
        gui.drawConfigs();
        gui.refresh();
        int key = 1;
        while (key != -1){
            key = menu.processConfigKey(gui.getKey());
            if(key == 2) {
                askForNDecks();
                break;
            }
        }
    }

    public void askForNDecks() throws IOException{
        int nDecks = 0;
        while(true){
            gui.drawAlterDecks(nDecks);
            gui.refresh();
            KeyStroke key = gui.getKey();
            if(key.getKeyType() == KeyType.Enter && nDecks != 0){
                break;
            }
            else if (key.getKeyType() == KeyType.Backspace) {
                nDecks=0;
            }
            else if(key.getKeyType() == KeyType.Character && (int) key.getCharacter() > 48 && (int) key.getCharacter() <= 57){
                nDecks = (int) key.getCharacter() - 48;
            }
        }
        this.nDecks = nDecks;
    }
}

class GameEntryPoint {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = Game.getInstance();
        game.run();
    }
}