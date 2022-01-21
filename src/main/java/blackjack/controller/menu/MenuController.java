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

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);
                else if (getModel().isSelectedConfigs()) game.setState(new ConfigsMenuState(new ConfigsMenu()));
                else if (getModel().isSelectedStart()) {
                    Table.getInstance().clear();
                    game.setState(new BetState(new BetMenu()));
                }
                break;
            case QUIT: game.setState(null);
        }
    }
}
