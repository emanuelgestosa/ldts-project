package blackjack.controller.game;

import blackjack.Game;
import blackjack.controller.Controller;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.BetMenu;
import blackjack.model.menu.EndRoundMenu;
import blackjack.model.menu.Menu;
import blackjack.states.BetState;
import blackjack.states.MenuState;

import java.io.IOException;

import static blackjack.gui.GUI.ACTION.LEFT;
import static blackjack.gui.GUI.ACTION.RIGHT;

public class EndStateController extends Controller<EndRoundMenu> {

    public EndStateController(EndRoundMenu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, InterruptedException {
            if(action==RIGHT)
                getModel().nextEntry();
            else if (action==LEFT)
                getModel().previousEntry();
            selectQuit(game, action);
    }

    public void caseSelect(Game game){
        if (getModel().isSelectedNewRound()) {
            game.setState(new BetState(new BetMenu()));
            Table.getInstance().prepareNewRound();
        }
        else if (getModel().isSelectedExit()) game.setState(new MenuState(new Menu()));
    }
}
