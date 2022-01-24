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

public class TableSplitController extends TableController {
    public TableSplitController(Table table) {
        super(table);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws InterruptedException {
        if (getModel().getPlayer().getSplitHand().getValue() >= 21) {
            takeDealerTurn(game);
            return;
        }
        selectAction(game, action);
    }

    @Override
    public void endTurn(Game game){
        takeDealerTurn(game);
    }

    @Override
    public boolean splitCase(){
        return true;
    }
}
