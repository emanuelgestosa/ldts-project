package blackjack.controller.game;

import blackjack.Game;
import blackjack.controller.Controller;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.BetMenu;
import blackjack.model.menu.Menu;
import blackjack.states.BetState;
import blackjack.states.GameState;
import blackjack.states.MenuState;

import static blackjack.gui.GUI.ACTION.LEFT;
import static blackjack.gui.GUI.ACTION.RIGHT;

public class BetController extends Controller<BetMenu> {

    public BetController(BetMenu betmenu) {
        super(betmenu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (Table.getInstance().getPlayer().getBalance() < 50) {
            game.setState(new MenuState(new Menu()));
            return;
        }
            if(action==RIGHT)
                getModel().nextEntry();
            else if(action==LEFT)
                getModel().previousEntry();
            selectQuit(game, action);
    }

    public void caseSelect(Game game){
        if (getModel().isSelectedExit()) game.setState(new MenuState(new Menu()));
        else{
            int bet= getModel().valueSelected();
            if (Table.getInstance().getPlayer().getBalance() < bet) return;
            Table.getInstance().dealCards();
            Table.getInstance().getPlayer().getHand().setBet(bet);
            game.setState(new GameState(Table.getInstance()));
        }
    }
}
