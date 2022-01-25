package blackjack.controller.game;

import blackjack.Game;
import blackjack.controller.Controller;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.BetMenu;
import blackjack.model.menu.EndRoundMenu;
import blackjack.model.menu.Menu;
import blackjack.states.BetState;
import blackjack.states.EndState;
import blackjack.states.GameSplitState;
import blackjack.states.MenuState;

import static blackjack.gui.GUI.ACTION.LEFT;
import static blackjack.gui.GUI.ACTION.RIGHT;

public class TableController extends Controller<Table> {

    public TableController(Table table) {
        super(table);
    }

    public void step(Game game, GUI.ACTION action, long time) throws InterruptedException {
        if (getModel().getPlayer().getHand().getValue() >= 21) {
            endTurn(game);
            return;
        }
        selectAction(game, action);
    }

    public void takeDealerTurn(Game game){
        getModel().getDealer().takeTurn(Table.getInstance().getDeck());
        game.setState(new EndState(new EndRoundMenu()));
    }

    public void endTurn(Game game){
        if (!getModel().getPlayer().isSplit()) {
            takeDealerTurn(game);
        }
        else game.setState(new GameSplitState(getModel()));
    }

    public void selectAction(Game game, GUI.ACTION action){
        if(action==RIGHT)
                getModel().nextEntry();
        else if(action==LEFT)
                getModel().previousEntry();
        selectQuit(game, action);
    }

    public void caseSelect(Game game){
        if (getModel().isSelectedExit()) game.setState(new MenuState(new Menu()));
        else if (getModel().isSelectedHit()) getModel().getPlayer().hit(getModel().getDeck(), splitCase());
        else if (getModel().isSelectedStand()) {
            getModel().getPlayer().stand();
            endTurn(game);
        }
        else if (getModel().isSelectedDouble()) {
            if (!getModel().getPlayer().doubleDown(getModel().getDeck(), splitCase())) return;
            endTurn(game);
        }
        else if (getModel().isSelectedSplit()) getModel().getPlayer().split(getModel().getDeck());
    }

    public boolean splitCase(){
        return false;
    }
}
