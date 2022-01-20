package blackjack.controller.game;

import blackjack.Game;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.Menu;
import blackjack.states.MenuState;

public class TableController extends GameController {

    public TableController(Table table) {
        super(table);
    }

    public void step(Game game, GUI.ACTION action, long time) {
        switch(action) {
            case QUIT:
                game.setState(null);
                break;

        }
        if (getModel().getPlayer().getBalance() < 50) game.setState(new MenuState(new Menu()));
    }
}
