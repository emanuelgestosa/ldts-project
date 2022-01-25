package blackjack.controller.menu;

import blackjack.Game;
import blackjack.controller.Controller;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.ConfigsMenu;
import blackjack.model.menu.Menu;
import blackjack.states.MenuState;

import java.io.IOException;

import static blackjack.gui.GUI.ACTION.LEFT;
import static blackjack.gui.GUI.ACTION.RIGHT;

public class ConfigsMenuController extends Controller<ConfigsMenu> {
    public ConfigsMenuController(ConfigsMenu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, InterruptedException {
        if (action == RIGHT)
            getModel().nextEntry();
        else if (action == LEFT)
            getModel().previousEntry();
        selectQuit(game, action);
    }

    public void caseSelect(Game game){
        if (!getModel().isSelectedExit()) {
            Table.getInstance().setNDecks(getModel().valueSelected());
        }
        game.setState(new MenuState(new Menu()));
    }
}
