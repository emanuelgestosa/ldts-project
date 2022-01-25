package blackjack.controller;

import blackjack.Game;
import blackjack.gui.GUI;

import java.io.IOException;

import static blackjack.gui.GUI.ACTION.QUIT;
import static blackjack.gui.GUI.ACTION.SELECT;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, GUI.ACTION action, long time) throws IOException, InterruptedException;

    public void selectQuit(Game game, GUI.ACTION action){
        if(action ==  QUIT)
            game.setState(null);
        else if(action ==  SELECT)
            caseSelect(game);
    }

    public abstract void caseSelect(Game game);
}
