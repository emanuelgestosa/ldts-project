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
        if (getModel().getPlayer().getBalance() < 50) game.setState(new MenuState(new Menu()));
        switch(action) {
            case QUIT:
                game.setState(null);
                break;
            case RIGHT:
                getModel().nextEntry();
                break;
            case LEFT:
                getModel().previousEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(new MenuState(new Menu()));
                else if (getModel().isSelectedHit()) getModel().getPlayer().hit(getModel().getDeck());
                else if (getModel().isSelectedStand()) getModel().getPlayer().stand();
                else if (getModel().isSelectedDouble()) getModel().getPlayer().doubleDown(getModel().getDeck());
                break;
        }
    }
}
