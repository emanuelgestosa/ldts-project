package blackjack;

import blackjack.gui.LanternaGUI;
import blackjack.model.menu.Menu;
import blackjack.states.MenuState;
import blackjack.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    private static Game instance = null;

    private Game() throws IOException, URISyntaxException, FontFormatException {
        gui = new LanternaGUI(70, 12);
        this.state = new MenuState(new Menu());
    }

    public static Game getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void start() throws IOException {
        int FPS = 30;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();
            state.step(this, gui, startTime);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        gui.close();
    }

    public void setState(State state) {
        this.state = state;
    }
}

class GameEntryPoint {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Game game = Game.getInstance();
        game.start();
    }
}