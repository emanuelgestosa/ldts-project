package blackjack.controller.menu;

import blackjack.Game;
import blackjack.controller.Controller;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.BetMenu;
import blackjack.model.menu.ConfigsMenu;
import blackjack.model.menu.Menu;
import blackjack.states.BetState;
import blackjack.states.ConfigsMenuState;
import blackjack.states.GameState;
import blackjack.states.MenuState;

import java.io.IOException;

import static blackjack.gui.GUI.ACTION.DOWN;
import static blackjack.gui.GUI.ACTION.UP;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if(action==UP)
            getModel().previousEntry();
        else if(action==DOWN)
            getModel().nextEntry();
        selectQuit(game, action);
    }

    public void caseSelect(Game game){
        if (getModel().isSelectedExit()) game.setState(null);
        else if (getModel().isSelectedConfigs()) game.setState(new ConfigsMenuState(new ConfigsMenu()));
        else if (getModel().isSelectedStart()) {
            Table.getInstance().clear();
            game.setState(new BetState(new BetMenu()));
        }
    }
}
