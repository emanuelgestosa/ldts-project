package blackjack.controller.game;

import blackjack.Game;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.EndRoundMenu;
import blackjack.model.menu.Menu;
import blackjack.states.EndState;
import blackjack.states.GameSplitState;
import blackjack.states.MenuState;

import java.io.IOException;

public class TableSplitController extends GameController {
    public TableSplitController(Table table) {
        super(table);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws InterruptedException {
        if (getModel().getPlayer().getSplitHand().getValue() == 21) {
            getModel().getDealer().takeTurn(Table.getInstance().getDeck());
            game.setState(new EndState(new EndRoundMenu()));
            return;
        }
        if (getModel().getPlayer().getSplitHand().getValue() > 21) {
            getModel().getDealer().takeTurn(Table.getInstance().getDeck());
            game.setState(new EndState(new EndRoundMenu()));
            return;
        }
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
                else if (getModel().isSelectedHit()) getModel().getPlayer().hit(getModel().getDeck(), true);
                else if (getModel().isSelectedStand()) {
                    getModel().getPlayer().stand();
                    getModel().getDealer().takeTurn(Table.getInstance().getDeck());
                    game.setState(new EndState(new EndRoundMenu()));
                }
                else if (getModel().isSelectedDouble()) {
                    getModel().getPlayer().doubleDown(getModel().getDeck(), true);
                    getModel().getDealer().takeTurn(Table.getInstance().getDeck());
                    game.setState(new EndState(new EndRoundMenu()));
                }
                else if (getModel().isSelectedSplit()) getModel().getPlayer().split(getModel().getDeck());
                break;
        }
    }
}
